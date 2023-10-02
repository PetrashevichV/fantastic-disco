package by.news.bean;

import java.time.LocalDateTime;

public class News {
	private long id;
	private String title;
	private String brief;
	private String content;
	private String category;
	private LocalDateTime date;

	public News() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String titleString) {
		this.title = titleString;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("News [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", brief=");
		builder.append(brief);
		builder.append(", content=");
		builder.append(content);
		builder.append(", category=");
		builder.append(category);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}

}
