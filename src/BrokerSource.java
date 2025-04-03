public interface BrokerSource {
    void addBrokerListener(BrokerListener bl);
    void removeBrokerListener(BrokerListener bl);
    void updateBrokerListeners(BrokerEvent e);
}
