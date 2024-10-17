import java.util.ArrayList;
import java.util.List;

interface IPrototype<T> {
    T clone();
}

enum DocumentType {
    REPORT,
    ARTICLE,
    PRESENTATION
}

class Section implements IPrototype<Section> {
    private String title;
    private String content;

    public Section(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Section clone() {
        return new Section(this.title, this.content);
    }

    @Override
    public String toString() {
        return "Section: " + title + ", Content: " + content;
    }
}

class Image implements IPrototype<Image> {
    private String url;

    public Image(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Image clone() {
        return new Image(this.url);
    }

    @Override
    public String toString() {
        return "Image: " + url;
    }
}


class Document implements IPrototype<Document> {
    private String title;
    private String content;
    private DocumentType type;
    private List<Section> sections;
    private List<Image> images;

    public Document(String title, String content, DocumentType type) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.sections = new ArrayList<>();
        this.images = new ArrayList<>();
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public void addImage(Image image) {
        images.add(image);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

    @Override
    public Document clone() {
        Document clonedDocument = new Document(this.title, this.content, this.type);
        for (Section section : this.sections) {
            clonedDocument.addSection(section.clone());
        }
        for (Image image : this.images) {
            clonedDocument.addImage(image.clone());
        }
        return clonedDocument;
    }

    @Override
    public String toString() {
        return "Document: " + title + "\nType: " + type + "\nContent: " + content +
                "\nSections: " + sections + "\nImages: " + images;
    }
}

class DocumentManager {
    public Document createDocument(IPrototype<Document> prototype) {
        return prototype.clone();
    }
}

public class Main_3 {
    public static void main(String[] args) {
        // Создаем оригинальный документ типа REPORT
        Document originalDocument = new Document(
                "Annual Report", "This is the annual report content.", DocumentType.REPORT);
        originalDocument.addSection(new Section("Introduction", "This is the introduction."));
        originalDocument.addSection(new Section("Summary", "This is the summary."));
        originalDocument.addImage(new Image("http://example.com/report_image.jpg"));

        // Клонируем документ с помощью DocumentManager
        DocumentManager manager = new DocumentManager();
        Document clonedDocument = manager.createDocument(originalDocument);

        // Изменяем тип и содержимое клонированного документа
        clonedDocument.setTitle("Modified Report");
        clonedDocument.setType(DocumentType.ARTICLE); // Меняем тип документа
        clonedDocument.addSection(new Section("Additional Info", "Some new content."));

        // Выводим оба документа
        System.out.println("Original Document:\n" + originalDocument);
        System.out.println("\nCloned Document:\n" + clonedDocument);
    }
}
