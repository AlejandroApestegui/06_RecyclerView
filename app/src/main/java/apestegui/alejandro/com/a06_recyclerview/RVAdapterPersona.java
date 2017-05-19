package apestegui.alejandro.com.a06_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alejandro on 14/05/2017.
 */

public class RVAdapterPersona extends RecyclerView.Adapter<RVAdapterPersona.VHPersona> {

    private List<Persona> lstPersona;
    private RVAdapterPersona.onClickRVAdapterPersona onClickRVAdapterPersona;

    interface onClickRVAdapterPersona{
        void onClick(Persona persona);
    }

    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Persona persona = (Persona) v.getTag();
            if(onClickRVAdapterPersona!=null){
                onClickRVAdapterPersona.onClick(persona);
            }
        }
    };

    public RVAdapterPersona(RVAdapterPersona.onClickRVAdapterPersona onClickRVAdapterPersona) {
        this.onClickRVAdapterPersona = onClickRVAdapterPersona;
        lstPersona = new ArrayList<>();
    }

    @Override
    public VHPersona onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.persona_item, parent, false);
        VHPersona vhPersona = new VHPersona(view);

        return vhPersona;
    }
    public void agregarPersona(Persona persona){
        lstPersona.add(persona);
        notifyItemInserted(lstPersona.size()-1);
    }
    public void modificarPersona(Persona persona){
        for(int i = 0;i<lstPersona.size(); i++){
            if(persona.getId().equals(lstPersona.get(i).getId())){
                lstPersona.get(i).setNombre(persona.getNombre());
                lstPersona.get(i).setApellido(persona.getApellido());
                lstPersona.get(i).setDocumento(persona.getDocumento());
                lstPersona.get(i).setEdad(persona.getEdad());
                notifyItemChanged(i);
                break;
            }
        }
    }

    public void eliminarPersona(String id){
        for(int i = 0;i<lstPersona.size(); i++){
            if(id.equals(lstPersona.get(i).getId())){
                lstPersona.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
    }

    @Override
    public void onBindViewHolder(VHPersona holder, int position) {
        Persona persona = lstPersona.get(position);

        holder.tvNombre.setText(persona.getNombre()+"-"+persona.getApellido());
        holder.itemView.setOnClickListener(clickListener);
        holder.itemView.setTag(persona);
        holder.tvDocumento.setText(persona.getDocumento());
        holder.tvEdad.setText(""+persona.getEdad());
    }

    @Override
    public int getItemCount() {
        return lstPersona.size();
    }

    class VHPersona extends RecyclerView.ViewHolder{

        TextView tvNombre, tvEdad, tvDocumento;

        public VHPersona(View itemView) {
            super(itemView);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
            tvDocumento = (TextView) itemView.findViewById(R.id.tvDocumentoo);
            tvEdad = (TextView) itemView.findViewById(R.id.tvEdad);
        }
    }
}
