package hu.ait.aitforum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import hu.ait.aitforum.data.Post
import kotlinx.android.synthetic.main.activity_create_post.*

class CreatePostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
    }

    fun sendClick(v: View){
        uploadPost()
    }

    fun uploadPost() {
        val post = Post(
            FirebaseAuth.getInstance().currentUser!!.uid,
            FirebaseAuth.getInstance().currentUser!!.email!!,
            etTitle.text.toString(),
            etBody.text.toString()
        )

        var postsCollection = FirebaseFirestore.getInstance().collection(
            "posts")

        postsCollection.add(post)
            .addOnSuccessListener {
                Toast.makeText(this@CreatePostActivity,
                    "Post SAVED", Toast.LENGTH_LONG).show()

                finish()
            }
            .addOnFailureListener{
                Toast.makeText(this@CreatePostActivity,
                    "Error ${it.message}", Toast.LENGTH_LONG).show()
            }
    }
}
