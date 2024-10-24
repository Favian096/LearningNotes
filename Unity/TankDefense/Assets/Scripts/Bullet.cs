using UnityEngine;

public class Bullet : MonoBehaviour {

    void Start() {
        this.GetComponent<Renderer>().material.color = GameConfig.getRandomColor();
    }

    void Update() {
        if (this.inWord()) {
            this.transform.Translate(
                new Vector3(0, 0, GameConfig.bulletSpeed * Time.deltaTime),
                Space.Self);
        }
    }

    private bool inWord() {
        if (this.transform.position.x < -40 || this.transform.position.x > 40) {
            Destroy(this.gameObject);
            return false;
        }
        if (this.transform.position.y < -20 || this.transform.position.y > 40) {
            Destroy(this.gameObject);
            return false;
        }
        if (this.transform.position.z < -40 || this.transform.position.z > 40) {
            Destroy(this.gameObject);
            return false;
        }
        return true;
    }

}
