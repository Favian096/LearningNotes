using UnityEngine;

public class TankBase : MonoBehaviour {
    public GameObject barrel;
    public GameObject bullet;
    private GameObject currentEnemy;
    private Vector3 targetPosition;
    private Quaternion defaultRotate;
    private float invokeTime;
    protected bool fireStatus;
    public AudioSource shootAudio;

    public virtual void Start() {
        Color color = GameConfig.getRandomColor();
        this.GetComponent<Renderer>().material.color = color;
        this.barrel.GetComponent<Renderer>().material.color = color;

        this.currentEnemy = null;

        this.invokeTime = GameConfig.fireSpeed;

        this.defaultRotate = this.transform.rotation;

        this.fireStatus = true;
    }

    public virtual void Update() {

    }

    private void fire() {
        if (!this.fireStatus) {
            return;
        }
        this.invokeTime += Time.deltaTime;
        if (this.invokeTime >= GameConfig.fireSpeed) {
            this.invokeTime = 0;
            this.bullet.transform.localPosition = this.barrel.transform.position;
            this.bullet.transform.LookAt(this.targetPosition);
            this.shootAudio.Play();
            Instantiate(this.bullet);
        }
    }

    private void OnTriggerEnter(Collider other) {
        if (null == this.currentEnemy && other.gameObject.CompareTag("Enemy")) {
            this.currentEnemy = other.gameObject;
        }
    }

    private void OnTriggerStay(Collider other) {
        if (null == this.currentEnemy && other.gameObject.CompareTag("Enemy")) {
            this.currentEnemy = other.gameObject;
        }
        if (this.currentEnemy != other.gameObject) {
            return;
        }
        this.targetPosition = new Vector3(
            other.transform.position.x,
            this.transform.position.y,
            other.transform.position.z);
        this.transform.LookAt(this.targetPosition);
        this.fire();
    }

    private void OnTriggerExit(Collider other) {
        this.currentEnemy = null;
        this.resetRotate();
    }


    protected void resetRotate() {
        this.transform.rotation = this.defaultRotate;
    }

}

