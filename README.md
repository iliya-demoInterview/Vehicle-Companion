1. You can run this application from android studio or create a signed app bundle for internal distribution through 
Google play store internal test track or trough firebase
2. For architecture my choice is MVI + Clean architecture. MVI is a great match for compose and reactive programing. Pure UI events are implemented 
in ModifyVehicle in the :feature:shared module. I've also implemented multimodule architecture with dedicated modules for each feature and data, db, network.
I've also implemented onDeletePoiFromFavourites so the favourites toggle state is based on the favourites db flow.
3. I've added the following tests: PoiServiceShould in :network/test; DBTest in :db/androidTest; GarageUiTest in :feature:garage/androidTest; I've run the
tests by right clicking on them in android studio -> run Test
4. Error and empty state is handled in the viewModel and converted to UI values from a sealed family of classes. Afterwards we monitor this state and changed
the screen depending on its value.
5. I'll add maps to the project; I've also did not had an api key, because the required valid api key by setting up google cloud project with valid billing and this 
is a public repo. Generally we add map, then we add a list of Markers. Each marker contains coordinates, icon, onClick event. We can then group the markers into clusters.
Each commit would be a separated branch, merged after pull request, but I've skipped on branching due to limited time.
6. Project stock consists of :network:socket + :stock app. Run stock app from android studio