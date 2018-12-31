package listaccessing;

public class Record {
	
	private int id;    // unique in my design, such as your student ID
	public String name;
	public int zipcode;
	
	public Record(int id, String name, int zipcode) {
		super();
		this.id = id;
		this.name = name;
		this.zipcode = zipcode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record other = (Record) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
