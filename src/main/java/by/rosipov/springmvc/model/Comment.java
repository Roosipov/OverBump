package by.rosipov.springmvc.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.hibernate.annotations.Type;
//import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import by.rosipov.springmvc.model.Comment;

@Entity
@Table(name = "Comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "text")
	private String comm;

	@Column(name = "commID")
	private Integer commID;

	@Column(name = "commDate")
	// @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private java.util.Date commDate;

	public Integer getId() {
		return id;
	}

	public Date getCommDate() {
		return commDate;
	}

	public void setCommDate(Date commDate) {
		this.commDate = commDate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCommID() {
		return commID;
	}

	public void setCommID(Integer commID) {
		this.commID = commID;
	}

	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
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
		if (!(obj instanceof Comment))
			return false;
		Comment other = (Comment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comm=" + comm + ", commID=" + commID + ", commDate=" + commDate + "]";
	}

}