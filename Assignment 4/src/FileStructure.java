import java.util.ArrayList;
import java.util.Iterator;
public class FileStructure {
	private NLNode<FileObject> root;
	//used for storing fileTypes(I want to increase the parameter of the filesoftype method but i'm not sure if this is allowed, making another method to utilise the arraylist would be too ineffecient so I decided to create a new private instance variable)
	private ArrayList<String> fileTypes;
	public FileStructure (String fileObjectName) throws FileObjectException
	{
		//try to make file
			FileObject file = new FileObject (fileObjectName);
		//if this is a file, make root contain only one node
		if(!file.isDirectory())
		{
			root = new NLNode<FileObject>(file,null);
		}
		//if this is a folder
		else
		{
			//root 
			root = new NLNode<FileObject>(file,null);
			construct(root);
			
		}
		
		
	}
	private void construct(NLNode<FileObject> r)
	{
		//get data
		FileObject file = r.getData();
		//if node isn't the last one in the tree
		if(!file.isFile())
		{
			//get an iterator of the files contained inside the node
			Iterator record = file.directoryFiles();
			//While the folder contains stuff in it
			while(record.hasNext())
			{
				//take out the file/folder and iterate to the next object
				FileObject thing = (FileObject)record.next() ;
				//create a node of the folder/file and make its parent r(root)
				NLNode<FileObject> another = new NLNode<FileObject>(thing,r);
				//Add a child to r
				r.addChild(another);
				//repeat these steps now with the children nodes
				construct(another);
				
			}
		}
		//at the end of the program the tree would've been made
	}
	public NLNode<FileObject> getRoot()
	{
		//returns root
		return root;
	}
	public Iterator<String> filesOfType (String type)
	{
		//Make arrayList
		fileTypes = new ArrayList<String>();
		//using recursion to collect necessary data
		typeSum(root,type);
		//return ArrayList as iterator
		return fileTypes.iterator();
	}
	/*
	 * Alternative: Add a parameter in typeSum that is type ArrayList, this would allow the method to consistently use a ArrayList to complete this progress
	 */
	private void typeSum(NLNode<FileObject> r, String type)
	{
		//if we are looking at a file
				if(r.getData().isFile())
				{
					//check if the file ends with the type the user is looking for
					if(r.getData().getLongName().endsWith(type))
						fileTypes.add(r.getData().getLongName());
				}
		//otherwise
		else
		{
			//get a iterator of the chilrens of the node
			Iterator record = r.getChildren();
			//do this method on the childrens
			while(record.hasNext())
			{
				typeSum((NLNode<FileObject>)record.next(),type);
			}
		}
				//After this method completes we should have scanned and added all the files in the tree
	}
	//BELOW IS ALTERNATIVE
	/*
	 * public void typeSum(NLNode<FileObject> r, String type, ArrayList<String> record)
	 * {
	 * 	//if we are looking at a file
				if(r.getData().isFile())
				{
					//check if the file ends with the type the user is looking for
					if(r.getData().getLongName().endsWith(type))
						record.add(r.getData().getLongName());
				}
		//otherwise
		else
		{
			//get a iterator of the chilrens of the node
			Iterator record = r.getChildren();
			//do this method on the childrens
			while(record.hasNext())
			{
				typeSum((NLNode<FileObject>)record.next(),type, record);
			}
		}
	 * }
	 * public Iterator<String> filesOfType (String type)
	{
		//Make arrayList
		ArrayList<String>fileTypes = new ArrayList<String>();
		//using recursion to collect necessary data
		typeSum(root,type,fileTypes);
		//return ArrayList as iterator
		return fileTypes.iterator();
	 */
	public String findFile(String name)
	{
		//uses recursion to determine file
		return exists(name,root);
		
	}
	//the recursion method used in findFile
	public String exists(String name, NLNode<FileObject> r)
	{
		//if we get a file
		if(r.getData().isFile())
		{
			//check if the name matches
			if(r.getData().getName().equals(name))
				//if the name matches, return its long name
				return r.getData().getLongName();
			else
			{
				return "";
			}
		}
		//if we look at a dictionary/folder
		else
		{
			//lets do this method on every children
			Iterator record = r.getChildren();
			//while there is children
			while(record.hasNext())
			{ 
				//get whether each method returns a longname
				String w = exists(name, (NLNode<FileObject>)record.next());
				//if there is a longname, then return the longname so the program ends
				if(w != "")
					return w;
				//if not do nothing
			}
		}
		//if after all recursion and folders the name still isn't found in the program, return "" as required
		return "";
	}
}
