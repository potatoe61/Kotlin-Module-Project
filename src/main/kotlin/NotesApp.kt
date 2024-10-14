data class Archive(val id: Int, val name: String)
data class Note(val id: Int, val name: String, val content: String)

class NotesApp {
    val archives = mutableListOf<Archive>()
    val notes = mutableMapOf<Int, MutableList<Note>>()
    private var archiveCounter = 0
    private var noteCounter = 0

    fun start() {
        val ui = UserInterface(this)
        ui.start()
    }

    fun createArchive() {
        println("Введите название архива:")
        val name = readLine() ?: return
        archives.add(Archive(++archiveCounter, name))
        notes[archiveCounter] = mutableListOf()
        println("Архив '$name' успешно создан.")
    }

    fun displayArchives() {
        if (archives.isEmpty()) {
            println("Нет доступных архивов.")
        } else {
            archives.forEach { archive ->
                println("${archive.id}. ${archive.name}")
            }
        }
    }

    fun createNote(archiveId: Int) {
        println("Введите название заметки:")
        val name = readLine() ?: return
        println("Введите содержимое заметки:")
        val content = readLine() ?: return
        notes[archiveId]?.add(Note(++noteCounter, name, content))
        println("Заметка '$name' успешно добавлена.")
    }

    fun displayNotes(archiveId: Int) {
        notes[archiveId]?.forEach { note ->
            println("${note.id}. ${note.name}")
        } ?: println("Нет заметок в этом архиве.")
    }

    fun viewNote(archiveId: Int, noteIndex: Int) {
        val selectedNote = notes[archiveId]?.get(noteIndex - 1)
        selectedNote?.let {
            println("Содержимое заметки '${it.name}': ${it.content}")
        } ?: println("Заметка не найдена.")
    }
}
