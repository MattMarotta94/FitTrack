import FitTrackClient from "../api/fitTrackClient";
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";


class ViewWorkout extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addWorkoutToPage', 'showEdits', 'submitEdits', 'showDelete', 'submitDelete', 'submitCancel'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addWorkoutToPage);
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const workoutDate = urlParams.get('date');
        document.getElementById('loading-message').innerText = "Loading Workout ...";
        const workout = await this.client.getWorkout(workoutDate);
        document.getElementById('loading-message').innerText = "Your Workout!"
        this.dataStore.set('workout', workout);

    }

    mount() {
        this.header.addHeaderToPage();
        document.getElementById('edit-button').addEventListener('click', this.showEdits);
        document.getElementById('create').addEventListener('click', this.submitEdits);
        document.getElementById('delete-button').addEventListener('click', this.submitDelete);
        document.getElementById('cancel').addEventListener('click', this.submitCancel);
      

        this.client = new FitTrackClient();
        this.clientLoaded();
    }

    addWorkoutToPage() {
        const workout = this.dataStore.get('workout');
        if (workout == null) {
            return
        }

        document.getElementById('workout-name').innerText = workout.name;
        document.getElementById('workout-date').innerText = workout.date;
        document.getElementById('workout-exercises').innerText = workout.exercises;
        document.getElementById('workout-notes').innerText = workout.notes;
    }

    showEdits() {
        var editFields = document.getElementById('edit-fields');
        const workout = this.dataStore.get('workout');
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

        const submitButton = document.getElementById('create');
        const origButtonText = submitButton.innerText;
        submitButton.innerText = 'Making Changes...';

        const workoutDate = document.getElementById('date').value;
        const workoutName = document.getElementById('name').value;
        const workoutExercises = document.getElementById('exercises').value;
        const workoutNotes = document.getElementById('notes').value;

        const updatedWorkout = await this.client.updateWorkout(workoutDate, workoutName, workoutExercises, workoutNotes,  (error) => {
            submitButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        const editsFields = document.getElementById('edit-fields');
        editsFields.classList.add('hidden');

        this.dataStore.set('workout', updatedWorkout);
    }

    showDelete() {
        var deleteButton = document.getElementById('delete-button');
        deleteButton.classList.remove('hidden');
    }

    async submitDelete(evt) {
        evt.preventDefault();

       const workout = this.dataStore.get('workout')

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

        window.location.href = `/index.html`;

        this.dataStore.set('workout', deletedWorkout);
       }
       
       else {
        return;
       }
    }
    

    submitCancel(evt) {
        evt.preventDefault();
        var editFields = document.getElementById('edit-fields');
        editFields.classList.add('hidden');
    }



}

const main = async () => {
    const viewWorkout = new ViewWorkout();
    viewWorkout.mount();
};

window.addEventListener('DOMContentLoaded', main);