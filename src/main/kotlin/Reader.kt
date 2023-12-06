import java.util.Scanner

class Reader<T>(var type: T) where T: Creater  {
    private var archiveList: MutableList<Archive> = mutableListOf()
    fun<T:Creater> showList(mainlist: MutableList<T>, type: Creater)
    {
        println("0. Создать")

        for(i in 1..mainlist.size)
        {
            val obj = mainlist[i-1]
            println("$i. ${obj.name}")
        }

        println("${mainlist.size + 1}. Выход")
        sayNumber(archiveList, mainlist.size, type)
    }

    private fun sayNumber(mainlist: MutableList<Archive>, listSize: Int, type: Creater)
    {
        while(true)
        {
            var arc = 0
            val number = Scanner(System.`in`).nextLine()
            if(number.toLongOrNull() == null) {
                println("Введите число от 0 до ${listSize + 1}"); continue
            }
            else
            {
                val num = number.toInt()
                if((num <= listSize + 1) and (num >= 0)) {
                    when (num) {
                        0 -> {
                            if (type is Archive) addArchive() else addNote(num)
                        }
                        listSize + 1 -> if(type is Archive) break else showList(archiveList, Archive("", "", mutableListOf()))
                        else -> {
                            if (type is Archive) {
                                showList(archiveList[num - 1].notes, Note("", "", mutableListOf()))
                                arc = num - 1
                            } else {
                                println(mainlist[arc].notes[num - 1].text)
                                println()
                                showList(archiveList[num - 1].notes, Note("", "", mutableListOf()))
                            }
                        }
                    }
                    break
                }
                else println("Введите число от 0 до ${listSize + 1}")
            }
        }
    }

    private fun addArchive()
    {
        while(true)
        {
            println("Введите название")
            val text = Scanner(System.`in`).nextLine()
            if(text.isEmpty()) println("Не может быть пустым!") else archiveList.add(Archive(text,"", mutableListOf())); break
        }
        showList(archiveList, Archive("", "", mutableListOf()))
    }

    private fun addNote(id: Int)
    {
        var name = ""
        while (true)
        {
            println("Введите название")
            name = Scanner(System.`in`).nextLine()
            if(name.isEmpty()) println("Не может быть пустым!") else break
        }
        while (true)
        {
            println("Запишите что-нибудь")
            val text = Scanner(System.`in`).nextLine()
            if(text.isEmpty()) {
                println("Не может быть пустым!"); continue
            } else {
                archiveList[id].notes.add(Note(name, text, mutableListOf()))
                break
            }
        }
        showList(archiveList[id].notes, Note("", "", mutableListOf()))
    }
}