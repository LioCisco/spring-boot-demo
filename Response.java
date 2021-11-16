import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response
{
	private int ret;
	private String msg;
	private Data data;

	public int getRet(){
		return ret;
	}

	public String getMsg(){
		return msg;
	}

	public Data getData(){
		return data;
	}
}
