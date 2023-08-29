package application.DTO;

import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Text {

	private SimpleIntegerProperty no;
	private SimpleStringProperty title;
	private SimpleStringProperty writer;
	private SimpleStringProperty content;
	private Date regDate;
	private Date updDate;
	
	public Text() {
		this("제목없음", "글쓴이없음", "내용없음");
	}
	
	public Text(String writer, String content, Date regDate, Date updDate) {
		this.writer = new SimpleStringProperty(writer);
		this.content = new SimpleStringProperty(content);
		this.regDate = regDate;
		this.updDate = updDate;
	}

	public Text(String title, String writer, String content) {
		this.title = new SimpleStringProperty(title);
		this.writer = new SimpleStringProperty(writer);
		this.content = new SimpleStringProperty(content);

	}

	public int getNo() {
		return no.get();
	}
	
	public void setNo(int no) {
		this.no = new SimpleIntegerProperty(no);
	}
	
	public String getTitle() {
		return title.get();
	}
	
	public void setTitle(String title) {
		this.title = new SimpleStringProperty(title);
	}
	
	public String getWriter() {
		return writer.get();
	}
	
	public void setWriter(String writer) {
		this.writer = new SimpleStringProperty(writer);
	}
	
	public String getContent() {
		return content.get();
	}
	
	public void setContent(String content) {
		this.content = new SimpleStringProperty(content);
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public Date getUpdDate() {
		return updDate;
	}
	
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	
	@Override
	public String toString() {
		return "Text [no=" + no + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regDate="
				+ regDate + ", updDate=" + updDate + "]";
	}
}
