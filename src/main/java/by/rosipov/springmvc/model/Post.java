package by.rosipov.springmvc.model;

import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import by.rosipov.springmvc.model.Post;

@Entity
@Table(name = "Posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "content", nullable = false)
	private byte[] content;

	@Column(name = "VIEWS", nullable = false)
	private Integer views;

	@Column(name = "description", length = 255)
	private String description;

	@Column(name = "type", length = 100, nullable = false)
	private String type;

	@Column(name = "country", length = 100, nullable = false)
	private String country;

	@Column(name = "city", length = 100, nullable = false)
	private String city;
	
	@Column(name = "bitChain", length = 64, nullable = false)
	private String bitChain;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "COMMENTS", nullable = false)
	private Integer comments;

	@Column(name = "bumps")
	private Integer bumps;

	@Column(name = "sage")
	private Integer sage;

	@Column(name = "JOINING_DATE")
	// @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private java.util.Date joiningDate;

	public java.util.Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(java.util.Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Integer getBumps() {
		return bumps;
	}

	public void setBumps(Integer bumps) {
		this.bumps = bumps;
	}

	public Integer getSage() {
		return sage;
	}

	public void setSage(Integer sage) {
		this.sage = sage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", name=" + name + ", content=" + Arrays.toString(content) + ", views=" + views
				+ ", description=" + description + ", type=" + type + ", country=" + country + ", city=" + city
				+ ", bitChain=" + bitChain + ", comments=" + comments + ", bumps=" + bumps + ", sage=" + sage
				+ ", joiningDate=" + joiningDate + "]";
	}

	public String getBitChain() {
		return bitChain;
	}

	public void setBitChain(String bitChain) {
		this.bitChain = bitChain;
	}

}