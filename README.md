# Rpsa
Rented parking spaces administration for Android Programming with Kotlin at the OTH Regensburg Wintersemester 2021/22


Functions: 

This app allows the user to add/update/delete customers into a room database. 
Via the TextInput fields the personal information for every customer can be added. An AutoCompleteTextView offers the 4 available vehicles that a customer can place
in his rented parking space. This AutCompleteTextView offers you to selected the vehicle via dropdown or giving you auto completion while typing the vehicle in the textfield.
![image](https://user-images.githubusercontent.com/91883770/147929055-db6b04d1-301a-4ee2-bb56-765440fb8410.png)









By clicking on the Add-Button the values were validated (for emptiness and correct format). If these checks were successful the data will be added to the Room-Database.

The Database Entity has 6 Columns: id(Int, Primary Key, Auto-Increment), lastName(String), firstName(String), telephone(Long), email(String), vehicle(String)
![image](https://user-images.githubusercontent.com/91883770/147929303-adf36d4d-d7f8-4934-937d-97a4acc7f5df.png)





The communication for add, update and delete is provided by the Dao (Data Access Object) interface with the @Dao anntotation. 
![image](https://user-images.githubusercontent.com/91883770/147929322-9dd2b118-341e-454a-83e0-6c94ba2fcbe8.png)







To complete the Room Database the RpsaDatabase.kt class with the @Database annotation. It is recommended that this class implements the Singleton pattern, which means
that only one instance of this class can be created. This can avoids data leakages.
![image](https://user-images.githubusercontent.com/91883770/147929385-6612a0ed-94a9-4016-bbd8-216c012232c5.png)





In the UI directly below the textfields, the CardLayout shows every entry in the database with its values. I´ve decided to place the database entries directly below
the input fields because in my opinion its easier to find the customer the user searches for and by clicking on the customer the values are prepared in the editable textfields
to update or delete the customer values. So no navigation between fragments or activites is not necessary. That´s just a design question but if my father or my 11 year old brother would use this App the might get along better without to much navigation. So scrolling up and down is a behaviour they might easier understand. 
![image](https://user-images.githubusercontent.com/91883770/147929496-5b70df34-f56e-4de8-be17-44bd861f8f11.png)









Design:
I´ve decided to use the MVVM (Model-View-ViewModel) Design pattern, which is recommended by Google. I created the packages with the same name, as well as 
repository, adapater, event and data packages to structure my Application. The view package is empty because the UI is created in the res/layout folder. But for completeness
i´ve added this package if programmable customizing to the view needs to be added in the future. 
![image](https://user-images.githubusercontent.com/91883770/147929261-56316ed0-2cc0-48f5-add1-cd38247dddc7.png)








Communication:
The Communication for DataBinding, LiveData and Coroutines happens in the CustomerViewModel.kt. The annotation @Bindable provides functions to get access to LiveData changes. The input validation and LiveData updates for the customer data and  the Add/Update and Clear/Delete buttons also happens in this class.
![image](https://user-images.githubusercontent.com/91883770/147930098-fb9bdc23-e555-43aa-a4ce-261ca7055991.png)





The CustomeRecyclerViewAdapater.kt with its inner class MyViewHolder represtens the Data Binding for the RecyclerView that displays the Customer Data
![image](https://user-images.githubusercontent.com/91883770/147929993-502df108-8ead-4d76-9a1d-f11590f3c384.png)








The RpsaMainViewModel.kt is the main entry point of this application. It initalizes the AutoCompleteTextView values, DataBinding, RecylcerView and the switch to toggle between ligth and night mode
![image](https://user-images.githubusercontent.com/91883770/147930441-584888c2-81ba-4c1f-8ec4-7deea6f8255a.png)





The light mode looks like this:
![image](https://user-images.githubusercontent.com/91883770/147930509-9b4e89aa-7019-4f57-8de4-b18d054dc709.png)



and if the switch is toggled the night mode will be activated on runtime:
![image](https://user-images.githubusercontent.com/91883770/147930557-af1ab378-4a14-49b1-945c-2ca1269103bd.png)





@author: tobiasdeisser


