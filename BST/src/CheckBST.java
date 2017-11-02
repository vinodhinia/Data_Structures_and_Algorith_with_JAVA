

public class CheckBST {

	public static int index=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static void copyBST(TreeNode root,int[] array){
		if(root == null) return;
		copyBST(root.left, array);
		array[index]=root.data;
		index++;
		copyBST(root.right,array);
	}
	
	public static boolean checkBST(TreeNode root){
		int[] array = new int[100];
		copyBST(root, array);
		for(int i=1;i<array.length;i++){
			if(array[i] < array[i-1]) return false;
		}
		return true;
	}
	
}
