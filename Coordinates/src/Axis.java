import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Axis {
	private DataType dt;
	private List<Double> numbers;
	private List<String> text;
	private String name;






	public Axis(String name , String type) {
		this.name =name;
		numbers = new ArrayList<>();
		text = new ArrayList<>();
		if(type.contentEquals("varchar") || type.contentEquals("char" )) {
			dt = DataType.TEXTUAL;
		}else {
			dt = DataType.NUMERIC;
		}

	}
	private void fetchData(ResultSet rs) throws SQLException {
		if(dt == DataType.TEXTUAL) {
			text.add(rs.getString(name));
		}else {
			numbers.add(rs.getDouble(name));
		}




	}

}
