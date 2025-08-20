public interface IProcess {
    public int getPid();
    public void getResource(Resource resource);
    public void releaseResources();
}