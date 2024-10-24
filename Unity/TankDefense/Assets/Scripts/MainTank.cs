using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class MainTank : TankBase {

    private Queue<GameObject> enemyList;

    public LineRenderer laser;
    public AudioSource laserAudio;
    private float laserAudioPlayTemp;

    public GameObject laserEnergyCanvas;
    private Slider laserEnergySlider;
    private float laserEnergyRestoreDalay;

    public override void Start() {
        base.Start();
        this.enemyList = new Queue<GameObject>();
        this.laserEnergySlider = this.laserEnergyCanvas.transform.GetChild(0).GetComponent<Slider>();
        this.laserAudioPlayTemp = 0.2f;
    }

    public override void Update() {
        base.Update();
        this.laserRay();
    }

    private void laserRay() {
        this.autoDetect();
        if (this.enemyList.Count > 0 && this.laserEnergySlider.value > 0) {
            this.transform.LookAt(new Vector3(
                this.enemyList.Peek().transform.position.x,
                this.transform.position.y,
                this.enemyList.Peek().transform.position.z));
            this.laser.gameObject.SetActive(true);

            this.laser.startWidth = GameConfig.laserStartWidth;
            this.laser.endWidth = GameConfig.laserEndWidth;
            this.laser.SetPosition(0, this.barrel.transform.position);
            this.laser.SetPosition(1, this.enemyList.Peek().transform.position);

            this.laserAudioPlayTemp += Time.deltaTime;
            if (this.laserAudioPlayTemp > 0.15f) {
                this.laserAudioPlayTemp = 0;
                this.laserAudio.Play();
            }

            if (this.enemyList.Peek().GetComponent<EnemyBase>().sufferDamage(GameConfig.laserDamage)) {
                this.laserEnergySlider.value += GameConfig.laserEnergyRestore;
            }

            this.laserEnergySlider.value -= GameConfig.laserEnergyReduce / GameConfig.laserEnergy;
        } else {
            this.laser.gameObject.SetActive(false);
            this.resetRotate();

            this.laserEnergyRestoreDalay += Time.deltaTime;
            if (this.laserEnergyRestoreDalay >= GameConfig.laserEnergyRestoreDalayTime) {
                this.laserEnergySlider.value += GameConfig.laserEnergyRestore;
                this.laserEnergyRestoreDalay = 0;
            }
        }

    }

    private void autoDetect() {
        this.enemyList.Clear();
        foreach (Collider enemy in Physics.OverlapSphere(this.transform.position, GameConfig.laserRange, LayerMask.GetMask("Enemy"))) {
            this.enemyList.Enqueue(enemy.gameObject);
        }
    }

}
