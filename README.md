<center><h1>Реализация бэкэнда для магазина книг</h1></center>
<center><h2>Содержание</h2></center>
<ul>
<li>Описание разработанного решения</li>
<li>Используемые технологии</li>
</ul>
<center><h2>Описание разработанного решения</h2></center>
<center><h3>Book</h3></center>
Объект класса Book является удобное представление книги и хранит в себе заголовок,количество и цену. Также класс содержит в себе методы по созданию объекта данного класса из
строки. При необходимости можно добавить поля автора,год издания и т.д.
<center><h3>Buyer</h3></center>
Объект класса Buyer позволяет хранить в себе данные покупателя, на данный момент - баланс и купленные книги.
Также его можно расширить добавляя логины, пароли, клубные системы(звания).
<center><h3>Shop</h3></center>
Самым глобавльным в проекте является класс Shop. Именно он обрабатывает запросы покупателей и манипулирует их балансом и книгами.
Посылая запросы магазину можно смотреть информацию товаре на "прилавке", просматривать уже купленные книги и покупать новые.
Сюда же можно добавить функцию поиска и другие команды, например стэк покупок, а также добавить серевер на фрэймворке spring.
<center><h2>Используемые технологии</h2></center>э
Сама программа была написана на языке java.Основные структуры данных - ассоциативные массивы.
Также присутствуют тесты, написанные с использованием junit5. 