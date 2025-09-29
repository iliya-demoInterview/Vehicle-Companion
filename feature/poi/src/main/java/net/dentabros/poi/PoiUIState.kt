package net.dentabros.poi


internal sealed class PoiUIState {
    data object Loading : PoiUIState()
    data object Error: PoiUIState()
    data class Success(val data : List<POI>): PoiUIState()
}