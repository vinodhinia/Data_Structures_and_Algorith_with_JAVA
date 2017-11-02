
public class DeleteNode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public TreeNode delete(TreeNode root,int data){
		if(root == null)return root;
		if(root.data <data){
			root.right = delete(root.right,data);
		}else if(root.data > data){
			root.left = delete(root.left,data);
		}else{
			//Case 1:When the node to be deleted is a leaf
			if(root.left==null && root.right ==null){
				root = null;
				return root;
			}
			
			
			//Case 2: Node to be Deleted has one Child
			if(root.right == null){
				TreeNode temp = root;
				root = root.left;
				//Delete root
				return root;
				
			}
			else if(root.left == null){
				TreeNode temp = root;
				root = root.right;
				//deallocate temp
				return root;
			}
			
			//Case 3: Node to be deleted has 2 chils
			else{
				TreeNode temp = findMin(root.right);
				root.data = temp.data;
				root.right = delete(root.right,temp.data);
				return root;
				
			}
		}
	}
	
	TreeNode findMin(TreeNode root){
		if(root == null) return root;
		
		while(root.left!=null){
			root=root.left;
		}
		return root;
	}

}
