package org.example.behavioral; 

import java.util.ArrayList;
import java.util.List;

/*    Паттерн Хранитель/Снимок (Memento) относится к категории поведенческих паттернов проектирования и используется для
сохранения и восстановления предыдущего состояния объекта, не раскрывая деталей его реализации. Паттерн позволяет
откатывать объект к предыдущему состоянию (например, реализация отмены операций).

    Структура паттерна:
1. Хранитель (Memento):
-Хранит внутреннее состояние объекта Originator.
-Защищает от доступа объектов, отличных от Originator.
2. Создатель (Originator):
-Создает хранителя, содержащего снимок его текущего внутреннего состояния.
-Использует хранителя для восстановления своего внутреннего состояния.
3. Опекун (Caretaker):
-Отвечает за хранение хранителя.
-Никогда не исследует и не использует содержимое хранителя.

    Пример использования паттерна Хранитель:
Рассмотрим пример с текстовым редактором, который поддерживает функцию отмены изменений.*/

// Хранитель - содержит состояние текстового редактора
class EditorMemento {
    private final String content;
    private final int cursorPosition;

    public EditorMemento(String content, int cursorPosition) {
        this.content = content;
        this.cursorPosition = cursorPosition;
    }

    public String getContent() {
        return content;
    }

    public int getCursorPosition() {
        return cursorPosition;
    }
}

// Создатель - Текстовый редактор
class TextEditor {
    private String content;
    private int cursorPosition;

    public TextEditor() {
        this.content = "";
        this.cursorPosition = 0;
    }

    public void write(String text) {
        content += text;
        cursorPosition += text.length();
        System.out.println("Writing: " + text);
    }

    public void setText(String text) {
        this.content = text;
        this.cursorPosition = text.length();
    }

    public void setCursor(int position) {
        if (position >= 0 && position <= content.length()) {
            this.cursorPosition = position;
        }
    }

    public String getContent() {
        return content;
    }

    public int getCursorPosition() {
        return cursorPosition;
    }

    // Создание снимка текущего состояния
    public EditorMemento save() {
        System.out.println("Saving editor state...");
        return new EditorMemento(content, cursorPosition);
    }

    // Восстановление состояния из снимка
    public void restore(EditorMemento memento) {
        this.content = memento.getContent();
        this.cursorPosition = memento.getCursorPosition();
        System.out.println("Editor state restored");
    }

    public void printContent() {
        System.out.println("Content: '" + content + "' (cursor at position " + cursorPosition + ")");
    }
}

// Опекун - История изменений
class EditorHistory {
    private List<EditorMemento> history = new ArrayList<>();

    public void backup(EditorMemento memento) {
        history.add(memento);
    }

    public EditorMemento undo() {
        if (history.isEmpty()) {
            return null;
        }
        int lastIndex = history.size() - 1;
        EditorMemento memento = history.get(lastIndex);
        history.remove(lastIndex);
        return memento;
    }

    public void showHistory() {
        System.out.println("History has " + history.size() + " saved states");
    }
}

// Пример использования
public class MementoPattern {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        EditorHistory history = new EditorHistory();

        // Пишем текст и сохраняем состояния
        System.out.println("--- Writing and saving states ---");
        editor.write("Hello ");
        editor.printContent();
        history.backup(editor.save());

        editor.write("World!");
        editor.printContent();
        history.backup(editor.save());

        editor.write(" This is a test.");
        editor.printContent();
        history.backup(editor.save());

        editor.write(" More text here.");
        editor.printContent();

        // Отменяем изменения
        System.out.println("\n--- Undoing changes ---");
        history.showHistory();

        EditorMemento memento = history.undo();
        if (memento != null) {
            editor.restore(memento);
            editor.printContent();
        }

        memento = history.undo();
        if (memento != null) {
            editor.restore(memento);
            editor.printContent();
        }

        memento = history.undo();
        if (memento != null) {
            editor.restore(memento);
            editor.printContent();
        }

        history.showHistory();
    }
}
/*
    Преимущества паттерна Хранитель:
1. Сохранение инкапсуляции: Не нарушает инкапсуляцию объекта, чье состояние сохраняется.
2. Упрощение структуры: Упрощает структуру Originator, избавляя его от необходимости хранить версии своего состояния.
3. Реализация отмены операций: Позволяет легко реализовать механизм отмены/повтора операций.

    Недостатки паттерна Хранитель:
1. Затраты памяти: Если Originator должен копировать большие объемы данных или клиенты создают и возвращают
хранителей создателю слишком часто, использование паттерна может привести к значительным затратам памяти.
2. Скрытые затраты: В некоторых языках программирования сложно гарантировать, что только Originator может
обращаться к состоянию хранителя.
3. Поддержка хранителей: Caretaker должен отслеживать время жизни хранителя и удалять устаревшие хранители.*/

