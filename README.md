# Rpsa
Rented parking spaces administration for Android Programming with Kotlin at the OTH Regensburg Wintersemester 2021/22


Functions: 

This app allows the user to add/update/delete customers into a room database. 
Via the TextInput fields the personal information for every customer can be added. An AutoCompleteTextView offers the 4 available vehicles that a customer can place
in his rented parking space.
![image](https://user-images.githubusercontent.com/91883770/147929055-db6b04d1-301a-4ee2-bb56-765440fb8410.png)









By clicking on the Add-Button the values were validated (for emptiness and correct format). If these checks were successful the data will be added to the Room-Database.

The Database Entity has 6 Columns: id(Int, Primary Key, Auto-Increment), lastName(String), firstName(String), telephone(Long), email(String), vehicle(String)
******





The communication for add, update and delete is provided by the Dao (Data Access Object) interface with the @Dao anntotation. 
*******






To complete the Room Database the RpsaDatabase.kt class with the @Database annotation. It is recommended that this class implements the Singleton pattern, which means
that only one instance of this class can be created. This can avoids data leakages.




In the UI directly below the textfields, the CardLayout shows every entry in the database with its values. I´ve decided to place the database entries directly below
the input fields because in my opinion its easier to find the customer the user searches for and by clicking on the customer the values are prepared in the editable textfields
to update or delete the customer values. So no navigation between fragments or activites is not necessary. That´s just a design question but if my father or my 11 year old brother would use this App the might get along better without to much navigation. So scrolling up and down is a behaviour they might easier understand. 










Design:
I´ve decided to use the MVVM (Model-View-ViewModel) Design pattern, which is recommended by Google. I created the packages with the same name, as well as 
repository, adapater, event and data packages to structure my Application. The view package is empty because the UI is created in the res/layout folder. But for completeness
i´ve added this package if programmable customizing to the view needs to be added in the future. 























@author: tobiasdeisser






