package final_lesson.model;

public class QueueMessage {

    private Country country;

    private QueueMessage(Country country) {
        this.country = country;
    }

    public static QueueMessage newMessage(Country country) {
        return new QueueMessage(country);
    }

    public static QueueMessage newEndMessage() {
        return new QueueMessage(null);
    }

    public boolean isEndMessage() {
        return country == null;
    }

    public Country getCountry() {
        return country;
    }
}
