using UnityEngine;

public class Landmine : MonoBehaviour {
    private GameObject mainTank;
    public GameObject explosion;
    private Vector3 mouseDownOffset;
    private Color originColor;
    private Vector3 originPosition;
    private bool ready;
    void Start() {
        this.mainTank = GameObject.Find("MainTank");
        this.originColor = this.gameObject.GetComponent<Renderer>().material.color;
        this.originPosition = this.transform.position;
        this.ready = false;
    }


    void Update() {
        this.detectEnemy();
    }

    private void detectEnemy() {
        if (!this.ready) {
            return;
        }
        foreach (var enemy in Physics.OverlapSphere(
            this.transform.position,
            GameConfig.landmineDetectEnemyRange,
            LayerMask.GetMask("Enemy"))) {
            this.transform.LookAt(enemy.transform.position);
            this.transform.Translate(new Vector3(0, 0, Time.deltaTime * GameConfig.landmineSpeed));
        }
    }

    private void OnTriggerEnter(Collider other) {
        if (this.ready && other.gameObject.CompareTag("Enemy")) {
            this.explosion.transform.position = this.transform.position;
            Instantiate(this.explosion);
            this.landmineExplosion();
        }
    }

    private void landmineExplosion() {
        foreach (var enemy in Physics.OverlapSphere(
            this.transform.position,
            GameConfig.landmineExplosionRange,
            LayerMask.GetMask("Enemy"))) {
            enemy.gameObject.GetComponent<EnemyBase>().sufferDamage(GameConfig.landmineDamage);
        }
        Destroy(this.gameObject);
    }

    private Vector3 mousePosition() {
        RaycastHit hit;
        Physics.Raycast(Camera.main.ScreenPointToRay(Input.mousePosition), out hit);
        return new Vector3(hit.point.x, transform.position.y, hit.point.z);
    }

    private void OnMouseDown() {
        this.gameObject.GetComponent<Renderer>().material.color = Color.white;

        this.mouseDownOffset = this.transform.position - mousePosition();
    }

    private void OnMouseDrag() {
        this.transform.position = this.mousePosition() + this.mouseDownOffset;

    }

    private void OnMouseUp() {
        this.gameObject.GetComponent<Renderer>().material.color = this.originColor;
        Collider[] routes = Physics.OverlapSphere(
            this.transform.position,
            GameConfig.landmineDetectRange,
            LayerMask.GetMask("Route"));

        foreach (var route in routes) {
            if (Vector3.Distance(this.transform.position, this.mainTank.transform.position) < GameConfig.awayFromMainTank) {
                break;
            }
            this.ready = true;
            return;
        }
        this.transform.position = this.originPosition;

    }


}
