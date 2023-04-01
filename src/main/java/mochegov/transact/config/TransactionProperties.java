package mochegov.transact.config;

public class TransactionProperties {

    private class TestException {
        private boolean isOn;

        public boolean isOn() {
            return isOn;
        }

        public void setOn(boolean on) {
            isOn = on;
        }
    }

    private TestException testException;

    public TestException getTestException() {
        return testException;
    }

    public void setTestException(TestException testException) {
        this.testException = testException;
    }
}
