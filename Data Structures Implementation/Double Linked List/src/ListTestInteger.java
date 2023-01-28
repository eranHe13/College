public class ListTestInteger extends ListTest<Integer>{
	static int numInstance = 0;
	@Override
	public Integer getParameterInstance() {
		return numInstance++;

	}

}
