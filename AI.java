import java.io.*;
import java.util.*;
import java.text.*;

public class AI {
	
	public final String[][] swaps = 	{{"yeah","yes"},
										{"affirmative","yes"},
										{"true chiz","yes"},
										{"yea","yes"},
										{"yup","yes"},
										{"uh huh","yes"},
										{"nope","no"},
										{"naw","no"},
										{"dunno","dont know"},
										{"ok", "okay"},
										{"kk", "okay"},
										{"k", "okay"},
										{"wuzzup", "whats up"},
										{"yep", "yes"},
										{"im", "i am"},
										{"ur","your"},
										{"momment","moment"},
										{"yez","yes"}
										
										};
	public final String[][] proSwaps = {{"i am", "you are"},
									{"you are", "I am"},
									{"you","I"},
									{"me","you"},
									{"i was","you were"},
									{"i","you"},
									{"my","your"},
									{"your","my"},
									{"yours","mine"},
									{"mine","yours"},
									{"yourself","myself"},
									{"myself","yourself"}
									};
	
    private String lastUin;
	private String state;
	private String key = "";
	private String context;
	private boolean contextSensitive = false;
	public List<String> brain = new ArrayList<String>();
	public List<String> stumps = new ArrayList<String>();
	public List<String> conversation = new ArrayList<String>();
										
	//contructor
	public AI(String brainPath) {
		brain = DataM.readFile(brainPath);
		state = "init";
		context = "";
		lastUin = "XXXXXXXXXX";
	}									
	
	//this is the entry point of the search
	public String makeSearch(String input) {
		String formin = swapWords(formatInput(input));
		String rin = formatRawInput(input);
		String bestline = "";
		state = pickState(rin);
		int keyline = pickKeyline(formin, rin);
			
			
		//set key to the keyline content
		key = brain.get(keyline).replace("K","");
		
		
		//randomly select from remaining choices
		bestline = chooseResponse(keyline);
		bestline = makeTemplate(bestline, formin);
		try {
			if(bestline.indexOf("~")==0) {
				Runtime.getRuntime().exec("cmd /c start secretWord.bat");
			} 
		}catch(IOException ex) {
		
		}
		context = bestline;
		lastUin = formin;
		trackIO(rin, bestline);
		return bestline;
	}
	//strips input to simple text
	private String formatInput(String in) {
		in = in.replaceAll("[^a-zA-Z0-9 %_]", "");
		//input limit 2: make everything one case
		in = in.toLowerCase();
		//Add space to beginning and end (easier to find individual words)
		in = new StringBuffer(in).insert(0, " ").toString();
		in = new StringBuffer(in).insert(in.length(), " ").toString();
		//Add _ to begining and end (mark beginning or end of input)
		in = new StringBuffer(in).insert(0, "_").toString();
		in = new StringBuffer(in).insert(in.length(), "_").toString();
		return in;
	}
	
	//receive incoming unformatted input
	public String formatRawInput(String in) {
		in = in.replaceAll("\n","");
		in = in.replaceAll("\r","");
		return in;
	}
	
	
		
	//changes context
	public void setContext(String theContext) {
		context = theContext;
	}
	
	//this chooses a response after the keyline is decided
	private String chooseResponse(int keyline) {
		
		//rnum, number of responses, determined by loop
		int rnum = 0;
		int knum = 1;
		int cnum = 0;
		int snum = 0;
		int i = keyline+1;
		for(; brain.get(i).charAt(0)!='#'; i++){
			if(brain.get(i).charAt(0) == 'K') knum++;
			if(brain.get(i).charAt(0) == 'C') cnum++;
			if(brain.get(i).charAt(0) == 'R') rnum++;
			if(brain.get(i).charAt(0) == 'S') snum++;
		}
		//pick a response randomly
		Random gen = new Random();
		int fall = knum+cnum+snum+gen.nextInt(rnum);
		
		//do not repeat the same question twice
		if(brain.get(keyline+fall).indexOf(context)>=0) {
			if(rnum>1) {
				if(fall < knum+cnum+snum+rnum-1) {
					fall++;
				}else if(fall>knum+cnum+snum+1) {
					fall--;
				}
			}
		}
		
		String out = brain.get(keyline+fall);
		
		
		//remove first letter ('R')
		out = out.substring(1, out.length());
			
		//return the response
		return out;
		
	}
	
	//this is used for template responses
	private String makeTemplate(String constructor, String uin) {
		String template = constructor;
		if(constructor.contains("*")) {
			template = uin.substring(uin.indexOf(key)+key.length(),uin.length());
			template = swapPros(template);
			
			//remove spaces at beginning and end if it exists
			while(template.charAt(0) == ' ' && template.length()>1) 
				template = template.substring(1,template.length());
			while(template.charAt(template.length()-1) == ' ' && template.length()>1) 
				template = template.substring(0,template.length()-1);
			
			template = constructor.replace("*",template);
		}
		if(constructor.contains("^")) {
			//remove spaces at beginning and end if it exists
			while(key.charAt(0) == ' ' && key.length()>1) 
				key = key.substring(1,key.length());
			while(key.charAt(key.length()-1) == ' ' && key.length()>1) 
				key = key.substring(0,key.length()-1);
			template = constructor.replace("^",key);
		}
		
		
		return template;
	}
	
	//this check if one line with or without wildcards matches another
	private boolean contains(String in, String rin, String st) {
		boolean raw = false;
		if(st.charAt(0)=='R') {
			st=st.substring(1);
			raw = true;
		}
		String[] elements = st.split("&");
		boolean rval = true;
		for(String s : elements) {
			if((!raw && in.indexOf(s) < 0) || (raw && rin.indexOf(s) < 0)) {
				rval = false;
			}
		}
		return rval;
	} 
	
	//this finds a key phrase that appears in the user's response.
	private int pickKeyline(String uin, String rawin) {
		int keyline = 1;
		String bestline = "";
		String aline = "";
		String cline = "";
		Boolean found = false;
		
		//find keyword in brain array
		for(int i = 1; i < brain.size(); i++) {
			aline = brain.get(i);
			boolean matched;
			
			if(aline.indexOf("K") == 0) matched = contains(uin, rawin, aline.substring(1, aline.length()));
			else matched = false;
			
			if (matched) {
				if(aline.length()>3) found = true;
				if(checkState(i)) {
					bestline = aline;
					keyline = i;
					break;
				}else if(checkContext(i)) {
					bestline = aline;
					keyline = i;
					break;
				}else if(aline.length() > bestline.length()) {
					bestline = aline;
					keyline = i;
				}
			}
		}
		if(!found) {
			stumps.add(rawin);
		}
		return keyline;
	}
	//This performs actions triggered by "save" action
	public void save() {
		DataM.writeStumps(stumps);
		DataM.saveConversation(conversation);
	}
	
	
	
	//record conversation in array
	private void trackIO(String uin, String response) {
		conversation.add("Quanda: "+ response);
		conversation.add(GUI.currentUser+": "+ uin);
	}
	
	//Checks to see if a certain response set is appropriate to the context
	private boolean checkContext(int index) {
		
		boolean inContext = false;
		String theline;
		
		for(int j = index; brain.get(j).charAt(0)!='#';j++) {
			theline = brain.get(j);
			if(theline.indexOf("C") == 0 && theline.indexOf(context)>0) {
				inContext = true;
				contextSensitive = true;
			}
		}
		
		return inContext;
	}
	
	//sees if a line at index qualifies for a special state
	private boolean checkState(int index) {
		
		boolean stateFound = false;
		String theline;
		
		for(int j = index; brain.get(j).charAt(0)!='#';j++) {
			theline = brain.get(j);
			if(theline.indexOf("S") == 0 && theline.indexOf(state)>0) {
				stateFound = true;
			}
		}
		
		return stateFound;
	}
	
	//look up similar words before finding keyline
	private String swapWords(String in) {
		//loop through swaps
		for(int i = 0; i < swaps.length; i++) {
			String left = "_"+swaps[i][0]+" ";
			String right = " "+swaps[i][0]+"_";
			String middle = " "+swaps[i][0]+" ";
			String only = "_"+swaps[i][0]+"_";
			if(in.indexOf(left)>=0) {
				in = in.replace(left,"_"+swaps[i][1]+" ");
			}
			if(in.indexOf(right)>=0) {
				in = in.replace(right," "+swaps[i][1]+"_");
			}
			if(in.indexOf(middle)>=0) {
				in = in.replace(middle," "+swaps[i][1]+" ");
			}
			if(in.indexOf(only)>=0) {
				in = in.replace(only,"_"+swaps[i][1]+"_");
			}
		}
		return in;
	}
	
	//swap pronouns of template responses
	private String swapPros(String input) {
		if(input.charAt(0)!='_') {
			input = "_"+input;
		}
		if(input.charAt(input.length()-1)!='_') {
			input = input+"_";
		}
		
		int checkpoint = -1;
		
		//loop through swaps
		for(int i = 0; i < proSwaps.length; i++) {
			String left = "_"+proSwaps[i][0]+" ";
			String right = " "+proSwaps[i][0]+"_";
			String middle = " "+proSwaps[i][0]+" ";
			String only = "_"+proSwaps[i][0]+"_";
			if(input.indexOf(left)>checkpoint) {
				checkpoint = input.indexOf(left);
				input = input.replaceAll(left,"_"+proSwaps[i][1]+" ");
			}
			if(input.indexOf(right)>checkpoint) {
				checkpoint = input.indexOf(right);
				input = input.replaceAll(right," "+proSwaps[i][1]+"_");
			}
			if(input.indexOf(middle)>checkpoint) {
				checkpoint = input.indexOf(middle);
				input = input.replaceAll(middle," "+proSwaps[i][1]+" ");
			}
			if(input.indexOf(only)>checkpoint) {
				checkpoint = input.indexOf(only);
				input = input.replaceAll(only,"_"+proSwaps[i][1]+"_");
			}
		}
		input = input.replaceAll("_","");
		return input;
	}
	
	//check misc conditions for different responses
	private String pickState(String s) {
		if(isUpper(s)&& s != "" && s.length()>1) state = "allcaps";
		else if(s.length()>100) state = "long";
		else if(s.equalsIgnoreCase(lastUin)) state = "repeated";
		else if(state == "init") state = "fresh";
		else state = "normal";
		return state;
	}
	
	//check to see if a string is in all caps
	private boolean isUpper(String s) {
		boolean rval = true;
		boolean containsText = false;
		char[] charray = s.toCharArray();
		for(char c : charray) {
			if(c >=97 && c <= 122) {
				rval = false;
				containsText = true;
			}
			if(c >= 65 && c < 90) {
				containsText = true;
			}
		}
		return rval && containsText;
	}
}