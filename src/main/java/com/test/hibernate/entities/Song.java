package com.test.hibernate.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "songs")
public class Song extends Model {

	private static final long serialVersionUID = -4014113842050905265L;

	@ManyToMany
	@JoinTable(name = "song_authors", joinColumns = { @JoinColumn(name = "song_id") }, inverseJoinColumns = { @JoinColumn(name = "author_id") })
	private Set<Author> authors = new HashSet<Author>();

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "genre_id", referencedColumnName = "id")
	private Genre genre;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "album_id", referencedColumnName = "id")
	private Album album;

	public Song() {
		super();
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

}
