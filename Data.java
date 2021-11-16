import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Data
{
	private List<Object> failStdTag;
	private List<String> passStdTag;
	private List<String> checkStdTag;
	private int status;

	public List<Object> getFailStdTag(){
		return failStdTag;
	}

	public List<String> getPassStdTag(){
		return passStdTag;
	}

	public List<String> getCheckStdTag(){
		return checkStdTag;
	}

	public int getStatus(){
		return status;
	}
}
