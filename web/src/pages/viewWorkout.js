import FitTrackClient from "../api/fitTrackClient";
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";


class ViewWorkout extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addWorkoutToPage'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addWorkoutToPage);
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const workoutDate = urlParams.get('date');
        document.getElementById('workout-name').innerText = "Loading Workout ...";
        const workout = await this.client.getWorkout(workoutDate);
        this.dataStore.set('workout', workout);

    }

    mount() {
        this.header.addHeaderToPage();

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
        document.getElementById('workout-notes').innerText = workout.notes;
    }
}

const main = async () => {
    const viewWorkout = new ViewWorkout();
    viewWorkout.mount();
};

window.addEventListener('DOMContentLoaded', main);