public class MockObserver implements Observer{
    private boolean updated = false;
    @Override
    public void update(Region region) {
        updated = true;
    }
    public boolean getUpdated(){
        return updated;
    }
    @Override
    public int getObserverID() {
        return 0;
    }
}
