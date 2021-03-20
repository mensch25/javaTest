package com.company;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String output = "";
        Stack<String> stack = new Stack<>();

        for (char c: input.toCharArray()) {
           if (c == '[') {
               //перед открывающей скобкой должно быть число повторений, проверяем является ли текущая верхушка стека числом.
               //Если да, то пушим скобку в стек, иначе выводим сообщение что строка не валидна
               try {
                   Integer.parseInt(stack.peek());
                   stack.push(String.valueOf(c));
               } catch (NumberFormatException | EmptyStackException e) {
                   System.out.println("Invalid input string");
                   return;
               }
           } else if (c == ']' && !stack.isEmpty()) {
               String tmp = "";
               //при закрывающей скобке раскручиваем стек, собираем подстроку, повторяем строку сколько указано раз и кладем назад
               //в стек, если стек заканчивается а открывающая скобка не была найдена, значит строка не валидна.
               while (!(stack.peek().equals("["))) {
                   tmp = stack.pop() + tmp;
                   if (stack.isEmpty()) {
                       System.out.println("Invalid input string");
                       return;
                   }
               }
               stack.pop();
               int repeatTimes = Integer.parseInt(stack.pop());
               stack.push(tmp.repeat(repeatTimes));
           } else if (Character.isDigit(c)) {
               //если верхушка стека число, то добавляем цифру к этому числу, иначе просто кладем цифру в стек
               try {
                   Integer.parseInt(stack.peek());
                   stack.push(stack.pop() + c);
               } catch (NumberFormatException | EmptyStackException e) {
                   stack.push(String.valueOf(c));
               }
           } else if ((int) c >= 65 && (int) c <= 122) {
                stack.push(String.valueOf(c));
           } else {
               //если ни одно из условий не выполнилось, значит строка содержит символы кроме латинских букв, квадратных скобок
               //и цифр, либо начинается с закрывающей квадратной скобки, а значит строка не валидна
               System.out.println("Invalid input string");
               return;
           }
        }

        //раскручиваем стек для получения выходной строки
        while (!stack.isEmpty()) {
            output = stack.pop() + output;
        }
        System.out.println(output);
    }
}
