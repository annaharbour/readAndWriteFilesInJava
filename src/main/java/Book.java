import lombok.Builder;

@Builder
public class Book {

    private String title;
    private Integer publishYear;
    private Boolean isFiction;
    private String publishingHouse;
    private String category;
    private String author;

    public String getTitle() {
        return title;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public Boolean getIsFiction() {
        return isFiction;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public String getCategory() {
        return category;
    }

    public String getAuthor() {
        return author;
    }
}