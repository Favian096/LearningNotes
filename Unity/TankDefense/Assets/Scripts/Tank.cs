using UnityEngine;

public class Tank : TankBase {
    private Vector3 mouseDownOffset;
    private Color originColor;
    private Vector3 originPosition;
    public override void Start() {
        base.Start();
        this.originColor = this.gameObject.GetComponent<Renderer>().material.color;
        this.originPosition = this.transform.position;
    }


    public override void Update() {
        base.Update();

    }

    private Vector3 mousePosition() {
        RaycastHit hit;
        Physics.Raycast(Camera.main.ScreenPointToRay(Input.mousePosition), out hit);
        return new Vector3(hit.point.x, transform.position.y, hit.point.z);
    }

    private void OnMouseDown() {
        this.gameObject.GetComponent<Renderer>().material.color = Color.white;
        this.barrel.gameObject.GetComponent<Renderer>().material.color = Color.white;

        this.mouseDownOffset = this.transform.position - mousePosition();
    }

    private void OnMouseDrag() {
        this.transform.position = this.mousePosition() + this.mouseDownOffset;
        this.fireStatus = false;
    }

    private void OnMouseUp() {
        this.fireStatus = true;
        this.gameObject.GetComponent<Renderer>().material.color = this.originColor;
        this.barrel.gameObject.GetComponent<Renderer>().material.color = this.originColor;
        Collider[] fortBases = Physics.OverlapSphere(
            this.transform.position,
            GameConfig.tankDetectRange,
            LayerMask.GetMask("FortBase"));


        foreach (var fort in fortBases) {
            if (fort.gameObject.GetComponent<FortBase>().isUsable && fort.gameObject.CompareTag("FortBase")) {
                this.transform.position = fort.gameObject.transform.position;
                fort.gameObject.GetComponent<FortBase>().isUsable = false;
                return;
            }
        }
        this.transform.position = this.originPosition;

    }


}
