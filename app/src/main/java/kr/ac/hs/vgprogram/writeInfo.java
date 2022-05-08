package kr.ac.hs.vgprogram;

public class writeInfo {
    private String title;
    private String content;
    private String publisher;

    public writeInfo(String title, String content,String publisher) {
        this.title=title;
        this.content=content;
        this.publisher=publisher;
    }

    public String getTitle(){return this.title; }
    public void setTitle(String title){ this.title=title;}
    public String getContent(){return this.content; }
    public void setContent(String content){ this.content=content;}
    public String getPublisher(){return this.publisher; }
    public void setPublisher(String publisher){ this.publisher=publisher;}

}