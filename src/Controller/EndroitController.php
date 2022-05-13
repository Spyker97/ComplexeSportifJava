<?php

namespace App\Controller;

use App\Entity\Endroit;
use App\Form\EndroitType;
use App\Repository\EndroitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;


/**
 * @Route("/endroit")
 */
class EndroitController extends AbstractController
{
    /**
     * @Route("/", name="app_endroit_index", methods={"GET"})
     */
    public function index(EndroitRepository $endroitRepository): Response
    {
        return $this->render('endroit/index.html.twig', [
            'endroits' => $endroitRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="app_endroit_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $endroit = new Endroit();
        $form = $this->createForm(EndroitType::class, $endroit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('image')->getData();
            if ($file != null){
                $fileName = md5(uniqid()).'.'.$file->guessExtension();
                $file->move($this->getParameter('upload_directory'),$fileName);
                $endroit->setImage($fileName);
            }
            $entityManager->persist($endroit);
            $entityManager->flush();
            return $this->redirectToRoute('app_endroit_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('endroit/new.html.twig', [
            'endroit' => $endroit,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_endroit_show", methods={"GET"})
     */
    public function show(Endroit $endroit): Response
    {
        return $this->render('endroit/show.html.twig', [
            'endroit' => $endroit,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="app_endroit_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Endroit $endroit, EndroitRepository $endroitRepository): Response
    {
        $form = $this->createForm(EndroitType::class, $endroit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $endroitRepository->add($endroit);
            return $this->redirectToRoute('app_endroit_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('endroit/edit.html.twig', [
            'endroit' => $endroit,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_endroit_delete", methods={"POST"})
     */
    public function delete(Request $request, Endroit $endroit, EndroitRepository $endroitRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$endroit->getId(), $request->request->get('_token'))) {
            $endroitRepository->remove($endroit);
        }

        return $this->redirectToRoute('app_endroit_index', [], Response::HTTP_SEE_OTHER);
    }

    /**
     * @Route("/endroit/ajax_search", name="ajax_search" ,methods={"GET"})
     * @param Request $request
     * @param EndroitRepository $endroitRepository
     * @return Response
     */
    public function searchAction(Request $request,EndroitRepository $endroitRepository) : Response
    {
        $em = $this->getDoctrine()->getManager();
        $requestString = $request->get('q');
        $endroits =$endroitRepository->SearchNom($requestString);
        if(!$endroits) {
            $result['endroits']['error'] = "endroit non trouvée ";
        } else {
            $result['endroits'] = $this->getRealEntities($endroits);
        }
        return new Response(json_encode($result));
    }
    public function getRealEntities($endroits){
        foreach ($endroits as $endroit){
            $realEntities[$endroit->getId()] = [$endroit->getImage(),$endroit->getNom()];

        }
        return $realEntities;
    }

    /**
     * @Route("/recherche/end", name="endroit_search")
     */
    public function recherche(Request $request, EndroitRepository $endroitRepository){
        $data=$request->get('data');
        $endroit=$endroitRepository->reche($data);
        return $this->render('endroit/index.html.twig', [
            'endroits' =>  $endroit,


        ]);


    }
    /**
     * @Route("/tri/end", name="endroit_tri")
     */
    public function Tri(Request $request,EndroitRepository $repository): Response
    {
        // Retrieve the entity manager of Doctrine
        $order=$request->get('type');
        if($order== "Croissant"){
            $endroits = $repository->tri_asc();
        }
        else {
            $endroits = $repository->tri_desc();
        }

        // Render the twig view
        return $this->render('endroit/index.html.twig', [
            'endroits' => $endroits
        ]);
}


}
