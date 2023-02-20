import FitTrackClient from '../api/fitTrackClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the create workout page of the website.
 */
class CreateWorkout extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewWorkout', 'searchCardio', 'searchCalisthenics', 'searchWeightLifting', 'cancel'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewWorkout);
        this.header = new Header(this.dataStore);
    
    }

    /**
     * Add the header to the page and load the WorkoutClient.
     */
    mount() {
        document.getElementById('create').addEventListener('click', this.submit);
        document.getElementById('cardio-button').addEventListener('click', this.searchCardio);
        document.getElementById('weightlifting-button').addEventListener('click', this.searchWeightLifting);
        document.getElementById('calisthenics-button').addEventListener('click', this.searchCalisthenics);
        document.getElementById('cancel').addEventListener('click', this.cancel);
        this.header.addHeaderToPage();
        this.client = new FitTrackClient();
    }

    /**
     * Method to run when the create Workout submit button is pressed. Call FitTrack to create the workout.
     */
    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = `error`;
        errorMessageDisplay.classList.add('hidden');

        const createButton = document.getElementById('create');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Creating Workout...';

        const workoutName = document.getElementById('workout-name').value;
        const workoutDate = document.getElementById('date').value;
        const workoutExercises = document.getElementById('exercises').value;
        const workoutNotes = document.getElementById('notes').value;

        const workout = await this.client.createWorkout(workoutName, workoutDate, workoutNotes, workoutExercises, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('workout', workout);
        }

    redirectToViewWorkout() {
        const workout = this.dataStore.get('workout');
        if(workout != null) {
            window.location.href = `/viewWorkout.html?date=${workout.date}`;
        }
    }


    async searchCardio(evt) {
        evt.preventDefault();
        const cardioButton = document.getElementById('cardio-button');
        const origButtonText = cardioButton.innerText;
        cardioButton.innerText = "Loading..";

        const Cardio = "Cardio";
        const jsonCardioList = await this.client.getExercises(`${Cardio}`);
        console.log(jsonCardioList);

        cardioButton.innerText = origButtonText;
        
        for (var i = 0; i < jsonCardioList.length; i++) {
            var exercise = jsonCardioList[i];
            var name = exercise.name;
            document.getElementById("cardioList").innerHTML += "<br>" + name + "</br>"
        }
    }

    async searchCalisthenics(evt) {
        evt.preventDefault();
        const calisthenicsButton = document.getElementById('calisthenics-button');
        const origButtonText = calisthenicsButton.innerText;
        calisthenicsButton.innerText = "Loading..";

        const Calisthenics = "Calisthenics";
        const jsonCalisthenicsList = await this.client.getExercises(`${Calisthenics}`);
        console.log(jsonCalisthenicsList);

        calisthenicsButton.innerText = origButtonText;
        
        for (var i = 0; i < jsonCalisthenicsList.length; i++) {
            var exercise = jsonCalisthenicsList[i];
            var name = exercise.name;
            document.getElementById("calisthenicsList").innerHTML += "<br>" + name + "</br>"
        }

    }

    async searchWeightLifting(evt) {
        evt.preventDefault();
        const weightliftingButton = document.getElementById('weightlifting-button');
        const origButtonText = weightliftingButton.innerText;
        weightliftingButton.innerText = "Loading..";

        const WeightLifting = "WeightLifting";
        const jsonWeightLiftingList = await this.client.getExercises(`${WeightLifting}`);
        console.log(jsonWeightLiftingList);

        weightliftingButton.innerText = origButtonText;
        
        for (var i = 0; i < jsonWeightLiftingList.length; i++) {
            var exercise = jsonWeightLiftingList[i];
            var name = exercise.name;
            document.getElementById("weightLiftingList").innerHTML += "<br>" + name + "</br>"
        }

    }

    cancel(evt) {
       evt.preventDefault();
       window.location.href = `/index.html`;
    }

}


/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createWorkout = new CreateWorkout();
    createWorkout.mount();
};

window.addEventListener('DOMContentLoaded', main);