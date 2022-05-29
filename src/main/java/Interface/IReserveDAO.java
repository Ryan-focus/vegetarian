package Interface;

import bean.Reserve;

public interface IReserveDAO {
	public Object insert(Reserve reserve);
	public void close();
}
