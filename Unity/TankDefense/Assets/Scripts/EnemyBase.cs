using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class EnemyBase : MonoBehaviour {
    private Queue<Vector3> landMarks = new Queue<Vector3>();
    private Vector3 currentLandMark;
    public GameObject explosion;
    public GameObject HPSliderCanvas;
    private GameObject mainCamera;
    protected Slider HPSlider;
    protected float HP;
    private float amountOfDamage = 0;
    protected float moveSpeed;
    protected int destoryCoins;
    protected int mainTankLifeReduce;


    public virtual void Start() {
        //使用队列实现
        // foreach (var landMark in GameObject.FindGameObjectsWithTag("LandMark")) {
        //     landMarks.Enqueue(new Vector3(
        //         landMark.transform.position.x,
        //         this.transform.position.y,
        //         landMark.transform.position.z));
        // }

        List<Vector3> landMarksTemp = new List<Vector3>();

        foreach (var landMark in GameObject.FindGameObjectsWithTag("LandMark")) {
            landMarksTemp.Add(new Vector3(
                landMark.transform.position.x,
                this.transform.position.y,
                landMark.transform.position.z));
        }

        Vector3 mainTankPositin = GameObject.Find("MainTank").transform.position;
        landMarksTemp.Sort((a, b) => {
            return Vector3.Distance(a, mainTankPositin) <= Vector3.Distance(b, mainTankPositin) ? 1 : -1;
        });

        foreach (var landMark in landMarksTemp) {
            landMarks.Enqueue(landMark);
        }

        this.currentLandMark = landMarks.Dequeue();

        this.GetComponent<Renderer>().material.color = GameConfig.getRandomColor();

        this.mainCamera = GameObject.FindGameObjectWithTag("MainCamera");

        this.mainTankLifeReduce = GameConfig.mainTankReduce;

        this.HPSliderCanvas = Instantiate(this.HPSliderCanvas);
        this.HPSliderCanvas.transform.SetParent(this.transform, true);
        this.HPSlider = HPSliderCanvas.transform.GetChild(0).GetComponent<Slider>();
        this.HPSliderCanvas.transform.position = new Vector3(
            this.transform.position.x,
            this.transform.position.y * 2.5f,
            this.transform.position.z);

    }

    public virtual void Update() {
        if (GameConfig.isStart) {
            this.move();
        }
    }

    protected void move() {
        if (Vector3.Distance(currentLandMark, this.transform.position) < 0.2f) {
            if (this.landMarks.Count <= 0) {
                Destroy(this.gameObject);

                GameConfig.mainTankHP -= this.mainTankLifeReduce;

                return;
            }
            this.currentLandMark = this.landMarks.Dequeue();
        }
        this.transform.LookAt(this.currentLandMark);
        this.transform.Translate(new Vector3(0, 0, Time.deltaTime * this.moveSpeed), Space.Self);
        this.HPSliderCanvas.transform.LookAt(this.mainCamera.transform.position);

    }

    private void OnTriggerEnter(Collider other) {
        if (other.gameObject.CompareTag("Bullet")) {
            this.sufferDamage(GameConfig.bulletDamage);
            Destroy(other.gameObject);
        }
    }

    public bool sufferDamage(float damage) {
        if (this.amountOfDamage < this.HP) {
            this.amountOfDamage += damage;
            this.HPSlider.value -= damage / this.HP;
            GameConfig.destoryEnemyDamage += damage;
            return false;
        } else {
            this.explosion.transform.localPosition = this.transform.position;
            GameConfig.goldCoin += this.destoryCoins;
            GameConfig.destoryEnemyNum++;
            Instantiate(this.explosion);
            Destroy(this.gameObject);
            return true;
        }
    }







}
