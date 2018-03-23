package com.test.hibernate.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
public class Genre extends Model {

	private static final long serialVersionUID = -5796235272265681440L;

	@OneToMany(mappedBy = "genre")
	private Set<Song> songs = new HashSet<Song>();

	@OneToMany(mappedBy = "genre")
	private Set<Album> albums = new HashSet<Album>();

	public Genre() {
		super();
	}

	public Set<Song> getSongs() {
		return songs;
	}

	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

}
