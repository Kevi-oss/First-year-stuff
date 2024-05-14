import  java.util.Comparator;
import java.util.Iterator;
public class NLNode<T> {
	private NLNode<T> parent;
	private ListNodes<NLNode<T>> children;
	private T data;
	public NLNode()
	{
		parent = null;
		children = new ListNodes<NLNode<T>>();;
		data = null;
	}
	public NLNode (T d, NLNode<T> p)
	{
		parent = p;
		data = d;
		children = new ListNodes<NLNode<T>>();
	}
	//setter: sets parent to p
	public void setParent(NLNode<T> p)
	{
		parent = p;
	}
	public NLNode<T> getParent()
	{
		return parent;
	}
	public void addChild(NLNode<T> newChild)
	{
		children.add(newChild);
	}
	public Iterator<NLNode<T>> getChildren()
	{
		return children.getList();
	}
	public Iterator<NLNode<T>> getChildren(Comparator<NLNode<T>> sorter)
	{
		return children.sortedList(sorter);
	}
	public T getData()
	{
		return data;
	}
	public void setData(T d)
	{
		data = d;
	}
}
