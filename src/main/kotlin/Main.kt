fun main() {
    println("Добро пожаловать в заметки!")
    Reader(Archive("", "", mutableListOf())).showList(mutableListOf(), Archive("", "", mutableListOf()))
    println("До свидания!")
}