import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;


public class Hauffman {
	
	protected int nId;
	
	protected node root;
	protected boolean show;
	protected String dotfilename;
	protected String s;
	
	protected TreeMap<Character, Integer> freqMap;
	protected HashMap<Character, String> encodeTable;
	protected HashMap<String, Character> decodeTable;
	
	protected String encodedString;
	protected String decodedString;

	public Hauffman(String s, boolean show, String dotfilename) {
		// TODO Auto-generated constructor stub
		this.s = s;
		this.show = show;
		this.dotfilename = dotfilename;
		freqMap = new TreeMap<Character, Integer>();
		encodeTable = new HashMap<Character, String>();
		decodeTable = new HashMap<String, Character>();
		
		getfrequency();
		buildTree();
		buildCodeTable(root,"");
		decode();
		encode();
		writeDot();
		
	}
	
	public void getfrequency() {
		for(int i=0;i<s.length();i++) {
			char t = s.charAt(i);
			if(freqMap.containsKey(s.charAt(i))) {
				freqMap.put(t, freqMap.get(t) + 1);
			}else {
				freqMap.put(t, 1);
			}
		}
		if(show) {
			System.out.println("Frequency Map");
			for(Entry<Character, Integer> m : freqMap.entrySet()) {
				System.out.println(m.getKey() +" " + m.getValue());
			}
		}
	}
	
	private void buildTree() {
		PriorityQueue<node> pq = new PriorityQueue<node>();
		for(Entry<Character, Integer> entry: freqMap.entrySet()) {
			pq.offer(new node(entry.getKey(),entry.getValue(), null, null));
		}
		
		while(pq.size() > 1) {
			node left = pq.poll();
			node right = pq.poll();
			node parent = new node('\0',left.frequencyCount + right.frequencyCount,left,right);
			pq.offer(parent);
		}
		root = pq.poll();
	}
	
	private void writeDot() {
        try {
	        PrintWriter output = new PrintWriter(dotfilename);
            String pdffilename = dotfilename.replace("dot", "pdf");
            output.println("## Vinodhini Asok Kumar ####");
            output.printf ("## dot -Tpdf %s -o %s\n", dotfilename, pdffilename);
            output.printf ("digraph g {\n");
            output.printf (" label = \"%s\"\n", s);
            Queue<node> queue = new LinkedList<node>();
            queue.add(root);
            while (!queue.isEmpty()) {
                node curr = queue.poll();
                if (curr.left != null) {
                    queue.add(curr.left);
                    output.printf(" \"%d\\n%d\" ->\"%d\\n%d%s\" [color=red]\n", 
                                  curr.nodeId, curr.frequencyCount, curr.left.nodeId, curr.left.frequencyCount,
                                  (curr.left.isLeaf() ? "\\n" + curr.left.ch : ""));
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                    output.printf(" \"%d\\n%d\" ->\"%d\\n%d%s\" [color=blue]\n", 
                                  curr.nodeId, curr.frequencyCount, curr.right.nodeId, curr.right.frequencyCount,
                                  (curr.right.isLeaf() ? "\\n" + curr.right.ch : ""));
                }
            }
	        output.flush();
	        output.close();
        }
        catch(IOException e) {
            System.out.println("Error writing into output file: " + dotfilename);
            System.exit(2);
	    }
    }

		
	
	public String encode() {
        encodedString = "";
        String tmp = "";
		for (int i = 0; i < decodedString.length(); ++i){
            tmp += decodedString.charAt(i);
			if (decodeTable.containsKey(tmp)) {
                encodedString += decodeTable.get(tmp);
                tmp = "";
			}
		}
        return encodedString;
    }
	
	public String decode() {
        decodedString = "";
        char ch;
        for (int i = 0; i < s.length(); ++i) {
            ch = s.charAt(i);
            decodedString += encodeTable.get(ch);
        }
        return decodedString;
	}
	
	public Hauffman() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		HauffmanTest t = new HauffmanTest();
//		t.test1(str, display, fname);
		Hauffman h = new Hauffman("aaabbggggghhhhaaaggggaaaaa_+@#", true, "huffman");
		Hauffman.node n = h.new node();
		
	}
	
	class node implements Comparable<node>{
		protected char ch;
		protected int nodeId;
		protected int frequencyCount;
		protected node left;
		protected node right;
		
		
		node(char ch,int frequencyCount, node left, node right){
			this.ch = ch;
			this.frequencyCount = frequencyCount;
			this.left = left;
			this.right = right;
		}
		
		public node() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public int compareTo(node o) {
			// TODO Auto-generated method stub
			return this.frequencyCount - o.frequencyCount;
		}
		
		public boolean isLeaf() {
			if(left == null && right == null)
				return true;
			return false;
		}
		
	}
	
	
	private void buildCodeTable(node n, String s) {
        if (!n.isLeaf()) {
            buildCodeTable(n.left,  s + '0');
            buildCodeTable(n.right, s + '1');
        }
        else {
            if (s == "") s = "0";
            encodeTable.put(n.ch, s);
            decodeTable.put(s, n.ch);
        }
    }


}
