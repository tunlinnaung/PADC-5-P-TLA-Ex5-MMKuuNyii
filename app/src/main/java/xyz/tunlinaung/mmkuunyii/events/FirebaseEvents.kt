package xyz.tunlinaung.mmkuunyii.events

import xyz.tunlinaung.mmkuunyii.data.vo.Job

class FirebaseEvents {

    class JobListLoadedEvent(val job: List<Job>)

}