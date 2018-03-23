package com.test.hibernate.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "authors")
public class Author extends Model {

	private static final long serialVersionUID = 8714241684605171676L;

	@ManyToMany(mappedBy = "authors")
	private Set<Song> songs = new HashSet<Song>();

	@OneToMany(mappedBy = "author")
	private Set<Album> albums = new HashSet<Album>();

	public Author() {
	}

	public Set<Song> getSongs() {
		return songs;
	}

	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return getId() + ". - " + getName();
	}

}
