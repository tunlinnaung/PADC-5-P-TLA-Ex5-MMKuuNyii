package xyz.tunlinaung.mmkuunyii.data.vo
import com.google.gson.annotations.SerializedName

data class Job(
        @SerializedName("applicant") var applicant: List<Applicant>? = emptyList(),
        @SerializedName("availablePostCount") var availablePostCount: Int? = 0,
        @SerializedName("email") var email: String? = "",
        @SerializedName("fullDesc") var fullDesc: String? = "",
        @SerializedName("genderForJob") var genderForJob: Int? = 0,
        @SerializedName("images") var images: List<String>? = emptyList(),
        @SerializedName("importantNotes") var importantNotes: List<String> = emptyList(),
        @SerializedName("interested") var interested: List<Interested> = emptyList(),
        @SerializedName("isActive") var isActive: Boolean = false,
        @SerializedName("jobDuration") var jobDuration: JobDuration? = null,
        @SerializedName("jobPostId") var jobPostId: Int? = 0,
        @SerializedName("jobTags") var jobTags: List<JobTag>? = emptyList(),
        @SerializedName("location") var location: String? = "",
        @SerializedName("makeMoneyRating") var makeMoneyRating: Int? = 0,
        @SerializedName("offerAmount") var offerAmount: OfferAmount? = null,
        @SerializedName("phoneNo") var phoneNo: String? = "",
        @SerializedName("postClosedDate") var postClosedDate: String? = "",
        @SerializedName("postedDate") var postedDate: String? = "",
        @SerializedName("relevant") var relevant: List<Relevant>? = emptyList(),
        @SerializedName("requiredSkill") var requiredSkill: List<RequiredSkill>? = emptyList(),
        @SerializedName("like") var like: Like? = null,
        @SerializedName("applyJob") var applyJob: Apply? = null,
        @SerializedName("shortDesc") var shortDesc: String? = "",
        @SerializedName("viewed") var viewed: List<Viewed>? = emptyList()
)

data class OfferAmount(
    @SerializedName("amount") var amount: Int? = 0,
    @SerializedName("duration") var duration: String? = ""
)

data class Relevant(
    @SerializedName("relevancyPercentage") var relevancyPercentage: Double? = 0.0,
    @SerializedName("seekerId") var seekerId: Int? = 0,
    @SerializedName("seekerName") var seekerName: String? = "",
    @SerializedName("seekerProfilePicUrl") var seekerProfilePicUrl: String? = "",
    @SerializedName("seekerSkill") var seekerSkill: List<SeekerSkill>? = emptyList(),
    @SerializedName("whyRelevant") var whyRelevant: String? = ""
)

data class SeekerSkill(
    @SerializedName("skillId") var skillId: Int? = 0,
    @SerializedName("skillName") var skillName: String? = ""
)

data class JobDuration(
    @SerializedName("jobEndDate") var jobEndDate: String? = "",
    @SerializedName("jobStartDate") var jobStartDate: String? = "",
    @SerializedName("totalWorkingDays") var totalWorkingDays: Int? = 0,
    @SerializedName("workingDaysPerWeek") var workingDaysPerWeek: Int? = 0,
    @SerializedName("workingHoursPerDay") var workingHoursPerDay: Int? = 0
)

data class Viewed(
    @SerializedName("seekerId") var seekerId: Int? = 0,
    @SerializedName("seekerName") var seekerName: String? = "",
    @SerializedName("seekerProfilePicUrl") var seekerProfilePicUrl: String? = "",
    @SerializedName("seekerSkill") var seekerSkill: List<SeekerSkill>? = emptyList(),
    @SerializedName("viewedTimeStamp") var viewedTimeStamp: String? = ""
)

data class JobTag(
    @SerializedName("desc") var desc: String? = "",
    @SerializedName("jobCountWithTag") var jobCountWithTag: Int? = 0,
    @SerializedName("tag") var tag: String? = "",
    @SerializedName("tagId") var tagId: Int? = 0
)

data class Applicant(
    @SerializedName("appliedDate") var appliedDate: String? = "",
    @SerializedName("canLowerOfferAmount") var canLowerOfferAmount: Boolean? = false,
    @SerializedName("seekerId") var seekerId: Int? = 0,
    @SerializedName("seekerName") var seekerName: String? = "",
    @SerializedName("seekerProfilePicUrl") var seekerProfilePicUrl: String? = "",
    @SerializedName("seekerSkill") var seekerSkill: List<SeekerSkill>? = emptyList(),
    @SerializedName("whyShouldHire") var whyShouldHire: List<WhyShouldHire>? = emptyList()
)

data class WhyShouldHire(
    @SerializedName("msg") var msg: String? = "",
    @SerializedName("timestamp") var timestamp: String? = "",
    @SerializedName("whyShouldHireId") var whyShouldHireId: Int? = 0
)

data class Interested(
    @SerializedName("interestedTimeStamp") var interestedTimeStamp: String? = "",
    @SerializedName("seekerId") var seekerId: Int? = 0,
    @SerializedName("seekerName") var seekerName: String? = "",
    @SerializedName("seekerProfilePicUrl") var seekerProfilePicUrl: String? = "",
    @SerializedName("seekerSkill") var seekerSkill: List<SeekerSkill>? = emptyList()
)

data class RequiredSkill(
    @SerializedName("skillId") var skillId: Int? = 0,
    @SerializedName("skillName") var skillName: String? = ""
)

data class Like(
    @SerializedName("likeId") var likeId: Long? = 0,
    @SerializedName("userId") var userId: String? = "",
    @SerializedName("isLike") var isLike: Boolean? = false,
    @SerializedName("timestamp") var timestamp: Long? = 0
)

data class Apply(
    @SerializedName("applyId") var applyId: Long? = 0,
    @SerializedName("userId") var userId: String? = "",
    @SerializedName("appliedDate") var appliedDate: Long? = 0
)