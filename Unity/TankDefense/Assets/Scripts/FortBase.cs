using UnityEngine;

public class FortBase : MonoBehaviour {
    internal bool isUsable;
    void Start() {
        this.isUsable = true;

    }

    void Update() {
        this.fortDetect();

    }

    private bool fortDetect() {
        foreach (Collider tank in
            Physics.OverlapSphere(
                this.transform.position,
                GameConfig.fortDetectRange,
                LayerMask.GetMask("Tank"))) {
            this.gameObject.GetComponent<Renderer>().material.color = Color.black;
            return true;
        }

        this.gameObject.GetComponent<Renderer>().material.color = Color.white;
        this.isUsable = true;
        return false;
    }


}
