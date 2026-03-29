import java.util.ArrayList;
import java.util.Collections;

// Node class for each song
class Song {
    String title;
    Song next;
    Song prev;

    public Song(String title) {
        this.title = title;
        this.next = null;
        this.prev = null;
    }
}

// Playlist class 
class Playlist {
    private Song head;
    private Song tail;
    private Song current; // pointer to current song

    public Playlist() {
        head = null;
        tail = null;
        current = null;
    }

    // Add song at the end
    public void addSong(String title) {
        Song newSong = new Song(title);
        if (head == null) {
            head = newSong;
            tail = newSong;
            current = head;
        } else {
            tail.next = newSong;
            newSong.prev = tail;
            tail = newSong;
        }
        System.out.println(title + " added to playlist.");
    }

    // Display playlist
    public void display() {
        if (head == null) {
            System.out.println("Playlist is empty");
            return;
        }
        Song temp = head;
        while (temp != null) {
            System.out.print(temp.title + " <-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // Delete a song
    public void deleteSong(String title) {
        if (head == null) {
            System.out.println("Playlist is empty");
            return;
        }

        Song temp = head;

        while (temp != null && !temp.title.equals(title)) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Song not found.");
            return;
        }

        if (temp.prev != null) {
            temp.prev.next = temp.next;
        } else {
            head = temp.next;
        }

        if (temp.next != null) {
            temp.next.prev = temp.prev;
        } else {
            tail = temp.prev;
        }

        if (current == temp) {
            current = temp.next != null ? temp.next : temp.prev;
        }

        System.out.println(title + " deleted from playlist.");
    }

    // Search for a song
    public void searchSong(String title) {
        Song temp = head;
        while (temp != null) {
            if (temp.title.equals(title)) {
                System.out.println("Song found: " + title);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Song not found: " + title);
    }

    // Play current song
    public void playCurrent() {
        if (current != null) {
            System.out.println("Playing: " + current.title);
        } else {
            System.out.println("Playlist is empty.");
        }
    }

    // Play next song
    public void nextSong() {
        if (current != null && current.next != null) {
            current = current.next;
            playCurrent();
        } else {
            System.out.println("No next song.");
        }
    }

    // Play previous song
    public void previousSong() {
        if (current != null && current.prev != null) {
            current = current.prev;
            playCurrent();
        } else {
            System.out.println("No previous song.");
        }
    }

    // Shuffle playlist
    public void shuffle() {
        if (head == null) return;

        // Step 1: Put all nodes into a list
        ArrayList<Song> list = new ArrayList<>();
        Song temp = head;
        while (temp != null) {
            list.add(temp);
            temp = temp.next;
        }

        // Step 2: Shuffle the list
        Collections.shuffle(list);

        // Step 3: Re-link nodes
        head = list.get(0);
        head.prev = null;
        temp = head;

        for (int i = 1; i < list.size(); i++) {
            temp.next = list.get(i);
            list.get(i).prev = temp;
            temp = temp.next;
        }
        tail = temp;
        tail.next = null;

        // Reset current pointer
        current = head;

        System.out.println("Playlist shuffled!");
    }
}

// Main class
public class MusicPlaylistManager {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();

        playlist.addSong("Shape of You");
        playlist.addSong("Believer");
        playlist.addSong("Perfect");
        playlist.addSong("Blinding Lights");
        playlist.addSong("Levitating");

        System.out.println("\nOriginal Playlist:");
        playlist.display();

        System.out.println("\nPlaying songs:");
        playlist.playCurrent();
        playlist.nextSong();
        playlist.nextSong();

        System.out.println("\nShuffling Playlist...");
        playlist.shuffle();

        System.out.println("\nShuffled Playlist:");
        playlist.display();

        System.out.println("\nPlaying after shuffle:");
        playlist.playCurrent();
        playlist.nextSong();
        playlist.previousSong();
    }
}