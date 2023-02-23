import FitTrackClient from "../api/fitTrackClient";
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/*
The code below this comment is equivalent to...
const EMPTY_DATASTORE_STATE = {
    'search-criteria': '',
    'search-results': [],
};

...but uses the "KEY" constants instead of "magic strings".
The "KEY" constants will be reused a few times below.
*/

const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_RESULTS_KEY]: [],
};


class GetWorkout extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'search', 'displayWorkout', 'showEdits', 'submitEdits', 'submitDelete', 'submitCancel', 'cancel', 'getAllWorkouts'], this);

        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.displayWorkout);
        console.log("getWorkout constructor");
    }


    mount() {
        document.getElementById('search-button').addEventListener('click', this.search);
        document.getElementById('search-button').addEventListener('click', this.displayWorkout);
        document.getElementById('edit-button').addEventListener('click', this.showEdits);
        document.getElementById('submit-edits').addEventListener('click', this.submitEdits);
        document.getElementById('delete-button').addEventListener('click', this.submitDelete);
        document.getElementById('cancel-edit').addEventListener('click', this.submitCancel);
        document.getElementById('cancel').addEventListener('click', this.cancel);
        document.getElementById('view-all').addEventListener('click', this.getAllWorkouts);
        
        this.header.addHeaderToPage();

        this.client = new FitTrackClient();
    }

    async getAllWorkouts(evt){
        evt.preventDefault();
        var success = true;
        document.getElementById('edits-card').classList.add('hidden');
        document.getElementById('workouts-list').innerHTML = "";

        const allButton = document.getElementById("view-all");
        const origButtonText = allButton.innerText;
        allButton.innerText = "Searching..."

       const results = await this.client.getAllWorkouts((error) => {
        errorMessageDisplay.innerText = `Looks like you haven't entered any workouts.`;
        errorMessageDisplay.classList.remove('hidden');
        success = false;
       });

        for (var i = 0; i < results.length; i++) {
            var workout = results[i];
            var date = workout.date;
            var name = workout.name;
            var exercises = workout.exercises;
            var notes = workout.notes;
            document.getElementById("workouts-list").innerHTML += "<br>" + "<a href=/viewWorkout.html?date=" + date + " " + "class=" + "searchAllResult" + ">" + "Date:" + " " + date + "</a>" + "</br>" + 
            "Name:" + " " + name + "<br>" + 
            "Exercises:" + " " + exercises + "<br/>" + 
            "Notes:" + " " + notes + "<br>"
        }

        allButton.innerText = origButtonText;

        if(success == true) {
            document.getElementById('all-workouts').classList.remove('hidden');
        }

    }

    async search(evt) {
        evt.preventDefault();
        var errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.classList.add('hidden');
        var success = true;
        document.getElementById('edits-card').classList.add('hidden');
        document.getElementById('all-workouts').classList.add('hidden');

        const searchButton = document.getElementById('search-button');
        const origButtonText = searchButton.innerText;
        searchButton.innerText = 'Searching...';

        const searchCriteria = document.getElementById('search-criteria').value;
        const results = await this.client.getWorkout(searchCriteria, (error) => {
            searchButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: Looks like you didn't workout that day. Try another.`;
            errorMessageDisplay.classList.remove('hidden');
            success = false;
            
        });

        searchButton.innerText = origButtonText;

        this.dataStore.set('results', results)

        if(success == true) {
            document.getElementById("edits-card").classList.remove("hidden");
        }

        else {
            return;
        }

    } 

    displayWorkout() {
        const workout = this.dataStore.get('results');
        if (workout == null) {
            return;
        }
        document.getElementById('workout-name').innerText = workout.name;
        document.getElementById('workout-date').innerText = workout.date;
        document.getElementById('workout-notes').innerText = workout.notes;
        document.getElementById('workout-exercises').innerText = workout.exercises;
    }

    showEdits() {
        console.log("Hit Show Edits");
        const workout = this.dataStore.get('results');
        console.log("workout", workout);
        document.getElementById('date').value = workout.date;
        document.getElementById('name').value = workout.name;
        document.getElementById('exercises').value = workout.exercises;
        document.getElementById('notes').value = workout.notes;
        var editFields = document.getElementById('edit-fields');
        editFields.classList.remove('hidden');
    }

    async submitEdits(evt) {
        evt.preventDefault();
     
        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = `error`;
        errorMessageDisplay.classList.add('hidden')

        const submitButton = document.getElementById('submit-edits');
        const origButtonText = submitButton.innerText;
        submitButton.innerText = 'Making Changes...';

        const workoutName = document.getElementById('name').value;
        const workoutDate = document.getElementById('date').value;
        const workoutNotes = document.getElementById('notes').value;
        const workoutExercises = document.getElementById('exercises').value;

        const updatedWorkout = await this.client.updateWorkout(workoutDate, workoutName, workoutExercises, workoutNotes,  (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        this.dataStore.set('results', updatedWorkout);

        const editsFields = document.getElementById('edit-fields');
        editsFields.classList.add('hidden');
       
    }

    async submitDelete() {
        
        const workout = this.dataStore.get('results')

        var confirmation = confirm('Are you sure you want to delete this workout?');
        
        if (confirmation == true) {

            const deleteButton = document.getElementById('delete-button');
            const origButtonText = deleteButton.innerText;
            deleteButton.innerText = 'Deleting..';

            const workoutDate = workout.date;

            const deletedWorkout =  await this.client.deleteWorkout(workoutDate, (error) => {
                errorMessageDisplay.innerText = `Error: ${error.message}`;
                errorMessageDisplay.classList.remove('hidden');
            });

            window.location.href = `/getWorkout.html`;

            this.dataStore.set('results', deletedWorkout);
            
        }

        else {
            return ;
        }

        
    }

    submitCancel(evt) {
        evt.preventDefault();
        var editFields = document.getElementById('edit-fields');
        editFields.classList.add('hidden');
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
    const getWorkout = new GetWorkout();
    getWorkout.mount();
};

window.addEventListener('DOMContentLoaded', main);