import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IRecipe } from 'src/recipe';
import { IPlace } from 'src/place';

@Component({
  selector: 'app-recipe-search',
  templateUrl: './recipe-search.component.html',
  styleUrls: ['./recipe-search.component.css']
})
export class RecipeSearchComponent implements OnInit {
  @ViewChild('recipe', { static: false }) recipes: ElementRef<HTMLInputElement> = {} as ElementRef;
  @ViewChild('place', { static: false }) places: ElementRef<HTMLInputElement> = {} as ElementRef;

  recipeValue: any;
  placeValue: any;

  venueList: IPlace[] = [];
  recipeList: IRecipe[] = [];

  currentLat: any;
  currentLong: any;
  geolocationPosition: any;

  convertLat: any;
  convertLong: any;
  geocode: string =  encodeURIComponent("39.097,-94.58"); // Kansas City

  constructor(private _http: HttpClient) {
  }

  ngOnInit() {
    window.navigator.geolocation.getCurrentPosition(
      position => {
        this.geolocationPosition = position;
        this.currentLat = position.coords.latitude;
        this.currentLong = position.coords.longitude;
      });
  }

  async getVenues() {

    this.recipeValue = this.recipes.nativeElement.value;
    this.placeValue = this.places.nativeElement.value;

    if (this.recipeValue !== null) {
      fetch(`https://api.edamam.com/api/recipes/v2?type=public&q=${this.recipeValue}&app_id=49ade2ef&app_key=3224e517a474cf2eb69f977986751d47`)
      .then(response => response.json())
      .then(data => {
          (data.hits).forEach((result: any) => {
            const recipeResult: IRecipe = {
              name: result.recipe.label,
              url: result.recipe.url,
              icon: result.recipe.image
            }
            this.recipeList.push(recipeResult);
          })
        }
      );

      // *** COULD NOT GET JSONP to callback correctly ***
      // this._http.jsonp(`https://api.edamam.com/api/recipes/v2?type=public&q=${this.recipeValue}&app_id=49ade2ef&app_key=3224e517a474cf2eb69f977986751d47`, 'callback').subscribe((data: any) => {      
      //   this.recipeResult = Object.keys(data.hits).map(k => {
      //     const index = data.hits[k];
      //     const recipeResult: IRecipe = {
      //       name: index.recipe.label,
      //       url: index.recipe.url,
      //       icon: index.recipe.image
      //     }
      //     this.recipeList.push(recipeResult);
      //   });
      // });
    }

    if (this.placeValue != null && this.placeValue !== '' && this.recipeValue != null && this.recipeValue !== '') {

      // const geolocation = await this.getGeocodes(this.placeValue);
      // console.log("out fetch => ", geolocation);

      const options = {
        method: 'GET',
        headers: {
          Accept: 'application/json',
          Authorization: 'fsq3GKoQk47nPZ0w1boKmB8FalPYTevOzbAOy4p8m2rWo2w='
        }
      };
      
      fetch(`https://api.foursquare.com/v3/places/nearby?ll=${this.geocode}&query=${this.recipeValue}`, options)
          .then(response => response.json())
          .then(data => {
            (data.results).forEach((result: any) => {
              const placeResult: IPlace = {
                name: result.name,
                location: result.location.formatted_address,
                uriCoordinates: encodeURIComponent(`${result.geocodes.main.latitude},${result.geocodes.main.longitude}`)
              }
              this.venueList.push(placeResult);
            })
          });


      /*** FOURSQUARE VERSION 2 - DEPRICATED ***/
    //   const versioning = (new Date()).toISOString().slice(0,10).replace(/-/g,""); //YYYYMMDD
      
    //   const options = {method: 'GET', headers: {Accept: 'application/json'}};

    //   fetch(`https://api.foursquare.com/v2/venues/search?client_id=A2FF55TLAWNKD0DX2NITTQ0JQ5MUGNTPOE2I2PM3IGO2R22O&client_secret=KBCVYJZBO54ZHOG3GNZCVGTJBRPSCJZVPB45UR3QTPC0C23U&query=${this.recipeValue}&near=${this.placeValue}&v=${versioning}`, options)
    //     .then(response => response.json())
    //     .then(response => console.log(response))
    //     .catch(err => console.error(err));
    // }
  }

  // getGeocodes(location: string) {
  //     // *** Convert place value into latitude/longitude geocodes
  //     return fetch(`https://api.mapbox.com/geocoding/v5/mapbox.places/${location}.json?access_token=pk.eyJ1Ijoid2luaXZpcyIsImEiOiJja3p6MWlyMjMwNXJsM2RxdzF3d2g1dnVkIn0.VMQsyu3pP6XyahyMKctn2w`)
  //       .then(response => response.json())
  //       .then(data => {
  //         this.convertLat = data.features[0].center[0];
  //         this.convertLong = data.features[0].center[1];
  //         this.geocode = encodeURIComponent(`${this.convertLat},${this.convertLong}`);
  //         console.log("in fetch => ", this.geocode);
  //         return this.geocode;
  //       });
  }
}
